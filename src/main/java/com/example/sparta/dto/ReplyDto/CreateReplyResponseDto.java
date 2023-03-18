package com.example.sparta.dto.ReplyDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReplyResponseDto {
    private Long reviewId;
    private String reviewUserEmail;
    private String reviewContent;
    private LocalDateTime reviewCreatedAt;
    private Long replyId;
    private String replyUserEmail;
    private String replyContent;
    private LocalDateTime replyCreatedAt;

    @Builder
    public CreateReplyResponseDto(
            Long reviewId,
            String reviewUserEmail,
            String reviewContent,
            LocalDateTime reviewCreatedAt,
            Long replyId,
            String replyUserEmail,
            String replyContent,
            LocalDateTime replyCreatedAt
    ) {
        this.reviewId = reviewId;
        this.reviewUserEmail = reviewUserEmail;
        this.reviewContent = reviewContent;
        this.reviewCreatedAt = reviewCreatedAt;
        this.replyId = replyId;
        this.replyUserEmail = replyUserEmail;
        this.replyContent = replyContent;
        this.replyCreatedAt = replyCreatedAt;
    }
}
