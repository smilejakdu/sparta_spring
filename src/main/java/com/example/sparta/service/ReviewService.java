package com.example.sparta.service;

import com.example.sparta.domain.Review;
import com.example.sparta.domain.User;
import com.example.sparta.dto.ReviewDto.CreateReviewRequestDto;
import com.example.sparta.dto.ReviewDto.CreateReviewResponseDto;
import com.example.sparta.dto.ReviewDto.UpdateReviewRequestDto;
import com.example.sparta.dto.ReviewDto.UpdateReviewResponseDto;
import com.example.sparta.repository.ReviewRepository;
import com.example.sparta.shared.Exception.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public CreateReviewResponseDto createReview(
            User user,
            CreateReviewRequestDto requestDto
    ) {
        Review newReview = Review.builder()
                .content(requestDto.getContent())
                .product(requestDto.getProduct())
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
    public Review getReview(
            Long id
    ) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new HttpException("해당 리뷰가 없습니다.", HttpStatus.BAD_REQUEST));
    }
}
