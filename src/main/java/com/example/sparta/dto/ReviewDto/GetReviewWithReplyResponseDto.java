package com.example.sparta.dto.ReviewDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GetReviewWithReplyResponseDto {
    private Long id;

    private String content;

    private String email;

    private List<GetReplyResponseDto> replyList;

    @Builder
    public GetReviewWithReplyResponseDto(
            Long id,
            String content,
            String email,
            List<GetReplyResponseDto> replyList
    ) {
        this.id = id;
        this.content = content;
        this.email = email;
        this.replyList = replyList;
    }
}

