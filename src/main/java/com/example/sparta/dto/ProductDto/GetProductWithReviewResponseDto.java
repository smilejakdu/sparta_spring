package com.example.sparta.dto.ProductDto;

import com.example.sparta.domain.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GetProductWithReviewResponseDto {
    private Long id;

    private String title;

    private String image;

    private List<Review> reviews;

    @Builder
    public GetProductWithReviewResponseDto(
            Long id,
            String title,
            String image,
            List<Review> reviews
    ) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.reviews = reviews;
    }
}
