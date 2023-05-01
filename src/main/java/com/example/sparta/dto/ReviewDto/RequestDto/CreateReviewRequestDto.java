package com.example.sparta.dto.ReviewDto.RequestDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateReviewRequestDto {
    private Long productId;

    private String content;

    private int score;

    @Builder
    public CreateReviewRequestDto(
            Long productId,
            String content,
            int score
    ) {
        this.productId = productId;
        this.content = content;
        this.score = score;
    }
}
