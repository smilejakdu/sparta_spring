package com.example.sparta.service;

import com.example.sparta.domain.Person;
import com.example.sparta.domain.Review;
import com.example.sparta.dto.ReviewDto.CreateReviewRequestDto;
import com.example.sparta.dto.ReviewDto.CreateReviewResponseDto;
import com.example.sparta.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public CreateReviewResponseDto createReview(
            Person person,
            CreateReviewRequestDto requestDto
    ) {
        Review newReview = Review.builder()
                .content(requestDto.getContent())
                .product(requestDto.getProduct())
                .score(requestDto.getScore())
                .person(person)
                .build();

        Review review = reviewRepository.save(newReview);
        return CreateReviewResponseDto.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .content(review.getContent())
                .username(review.getPerson().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
