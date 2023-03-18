package com.example.sparta.dto.ReviewDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReviewResponseDto {
    private Long id;

    private Long productId;

    private String content;

    private String username;

    private LocalDateTime createdAt;

    @Builder
    public CreateReviewResponseDto(
            Long id,
            Long productId,
            String content,
            String username,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.productId = productId;
    }
}
