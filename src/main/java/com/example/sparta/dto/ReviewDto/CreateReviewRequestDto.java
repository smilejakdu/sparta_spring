package com.example.sparta.dto.ReviewDto;

import com.example.sparta.domain.Product;
import lombok.Data;

@Data
public class CreateReviewRequestDto {

    private Product product;

    private String content;

    private int score;
}
