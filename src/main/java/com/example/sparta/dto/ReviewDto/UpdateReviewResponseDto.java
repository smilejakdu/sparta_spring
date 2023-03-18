package com.example.sparta.dto.ReviewDto;

import com.example.sparta.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateReviewResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private String email;
    private LocalDateTime createdAt;

    @Builder
    public UpdateReviewResponseDto(
            Long id,
            String content,
            Long userId,
            String email,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.email = email;
        this.createdAt = createdAt;
    }
}
