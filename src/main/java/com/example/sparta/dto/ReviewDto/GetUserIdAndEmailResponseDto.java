package com.example.sparta.dto.ReviewDto;

import lombok.Builder;
import lombok.Data;

@Data
public class GetUserIdAndEmailResponseDto {
    private Long userId;
    private String email;
    private Long reviewId;
    private String content;
    private int score;

    @Builder
    public GetUserIdAndEmailResponseDto(
            Long userId,
            String email,
            Long reviewId,
            String content,
            int score
    ) {
        this.userId = userId;
        this.email = email;
        this.reviewId = reviewId;
        this.content = content;
        this.score = score;
    }
}
