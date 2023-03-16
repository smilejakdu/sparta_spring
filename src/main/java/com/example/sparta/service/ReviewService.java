package com.example.sparta.service;

import com.example.sparta.domain.Product;
import com.example.sparta.domain.Reply;
import com.example.sparta.domain.Review;
import com.example.sparta.domain.User;
import com.example.sparta.dto.ReviewDto.*;
import com.example.sparta.repository.ProductRepository;
import com.example.sparta.repository.ReplyRepository;
import com.example.sparta.repository.ReviewRepository;
import com.example.sparta.shared.Exception.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        Review review = reviewRepository.save(newReview);
        return CreateReviewResponseDto.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .content(review.getContent())
                .username(review.getUser().getName())
                .createdAt(review.getCreatedAt())
                .build();
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
        Review savedReview = reviewRepository.save(foundReview);
        return UpdateReviewResponseDto
                .builder()
                .id(savedReview.getId())
                .content(savedReview.getContent())
                .user(savedReview.getUser())
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
        List<Reply> foundReplyList = replyRepository.findAllByReviewId(foundReview.getId());

        return GetReviewWithReplyResponseDto
                .builder()
                .id(foundReview.getId())
                .email(foundReview.getUser().getEmail())
                .content(foundReview.getContent())
                .replyList(foundReplyList)
                .build();
    }
}
