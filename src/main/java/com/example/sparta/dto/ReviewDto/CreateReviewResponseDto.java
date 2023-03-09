package com.example.sparta.dto.ReviewDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateReviewResponseDto {
    private Long id;

    private Long productId;

    private String content;

    private String username;

    private String createdAt;

    @Builder
    public CreateReviewResponseDto(
            Long id,
            Long productId,
            String content,
            String username,
            String createdAt
    ) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.productId = productId;
    }
}
