package com.example.sparta.dto.ReplyDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateReplyRequestDto {
    private Long reviewId;
    private String content;

    @Builder
    public CreateReplyRequestDto(
            Long reviewId,
            String content
    ) {
        this.reviewId = reviewId;
        this.content = content;
    }
}
