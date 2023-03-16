package com.example.sparta.dto.ReviewDto;

import lombok.Data;

@Data
public class GetReplyResponseDto {
    private Long id;

    private String content;

    private String email;
}
