package com.example.sparta.dto.ReviewDto;

import lombok.Data;

@Data
public class UpdateReviewRequestDto {
    private String content;
    private int score;
}
