package com.example.sparta.service;

import com.example.sparta.domain.Product;
import com.example.sparta.domain.Review;
import com.example.sparta.domain.User;
import com.example.sparta.dto.ReviewDto.*;
import com.example.sparta.dto.ReviewDto.RequestDto.CreateReviewRequestDto;
import com.example.sparta.dto.ReviewDto.ResponseDto.CreateReviewResponseDto;
import com.example.sparta.repository.ProductRepository;
import com.example.sparta.repository.ReplyRepository;
import com.example.sparta.repository.ReviewRepository;
import com.example.sparta.shared.Exception.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final ReplyRepository replyRepository;

    @Transactional
    public CreateReviewResponseDto createReview(
            User user,
            CreateReviewRequestDto requestDto
    ) {
        Product foundProduct = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new HttpException("해당 상품이 없습니다.", HttpStatus.BAD_REQUEST));

        Review newReview = Review.builder()
                .content(requestDto.getContent())
                .product(foundProduct)
                .score(requestDto.getScore())
                .user(user)
                .build();

        Review savedReview = reviewRepository.save(newReview);
        return CreateReviewResponseDto.builder()
                .id(savedReview.getId())
                .productId(savedReview.getProduct().getId())
                .content(savedReview.getContent())
                .username(savedReview.getUser().getName())
                .createdAt(savedReview.getCreatedAt())
                .build();
    }

    @Transactional
    public List<Review> getReviewByUser(
            User user
    ) {
        return reviewRepository.findReviewByUser(user);
    }

    @Transactional
    public UpdateReviewResponseDto updateReview(
            Long id,
            User user,
            UpdateReviewRequestDto requestDto
    ) {
        Review foundReview = reviewRepository.findByIdAndUser(id, user)
                        .orElseThrow(() -> new HttpException("해당 리뷰가 없습니다.", HttpStatus.BAD_REQUEST));

        foundReview.setContent(requestDto.getContent());
        foundReview.setScore(requestDto.getScore());
        Review savedReview = reviewRepository.save(foundReview);

        return UpdateReviewResponseDto
                .builder()
                .id(savedReview.getId())
                .content(savedReview.getContent())
                .userId(savedReview.getUser().getId())
                .email(savedReview.getUser().getEmail())
                .build();
    }

    @Transactional
    public Long deleteReview(
            Long id,
            User user
    ) {
        Review foundReview = reviewRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new HttpException("해당 리뷰가 없습니다.", HttpStatus.BAD_REQUEST));
        reviewRepository.deleteById(foundReview.getId());
        return foundReview.getId();
    }

    @Transactional
    public GetReviewWithReplyResponseDto getReviewWithReply(
            Long id
    ) {
        Review foundReview = reviewRepository.findById(id)
                .orElseThrow(() -> new HttpException("해당 리뷰가 없습니다.", HttpStatus.BAD_REQUEST));

        List<GetReplyResponseDto> foundReplyList = replyRepository.findAllByReviewId(foundReview.getId())
                .stream()
                .map(reply -> GetReplyResponseDto
                        .builder()
                        .id(reply.getId())
                        .email(reply.getUser().getEmail())
                        .content(reply.getContent())
                        .createdAt(reply.getReview().getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return GetReviewWithReplyResponseDto
                .builder()
                .id(foundReview.getId())
                .email(foundReview.getUser().getEmail())
                .content(foundReview.getContent())
                .replyList(foundReplyList)
                .build();
    }
}
