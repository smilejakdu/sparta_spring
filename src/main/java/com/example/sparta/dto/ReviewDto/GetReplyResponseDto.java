package com.example.sparta.dto.ReviewDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetReplyResponseDto {
    private Long id;
    private String content;
    private String email;
    private LocalDateTime createdAt;
    @Builder
    public GetReplyResponseDto(
            Long id,
            String content,
            String email,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.content = content;
        this.email = email;
        this.createdAt = createdAt;
    }
}
