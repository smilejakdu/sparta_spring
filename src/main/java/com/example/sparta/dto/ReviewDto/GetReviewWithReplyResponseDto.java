package com.example.sparta.dto.ReviewDto;

import com.example.sparta.domain.Reply;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GetReviewWithReplyResponseDto {
    private Long id;

    private String content;

    private String email;

    private List<Reply> replyList;

    @Builder
    public GetReviewWithReplyResponseDto(
            Long id,
            String content,
            String email,
            List<Reply> replyList
    ) {
        this.id = id;
        this.content = content;
        this.email = email;
        this.replyList = replyList;
    }
}

