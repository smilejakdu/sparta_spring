package com.example.sparta.dto.ReviewDto;

import com.example.sparta.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateReviewResponseDto {
    private Long id;

    private String content;

    private User user;

    @Builder
    public UpdateReviewResponseDto(
            Long id,
            String content,
            User user
    ) {
        this.id = id;
        this.content = content;
        this.user = user;
    }
}
