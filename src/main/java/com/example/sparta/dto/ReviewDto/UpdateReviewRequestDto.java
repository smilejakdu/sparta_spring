package com.example.sparta.dto.ReviewDto;

import com.example.sparta.domain.Product;
import lombok.Data;

@Data
public class UpdateReviewRequestDto {
    private Product product;

    private String content;

    private int score;
}
