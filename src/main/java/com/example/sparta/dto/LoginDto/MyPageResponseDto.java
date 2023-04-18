package com.example.sparta.dto.LoginDto;

import com.example.sparta.domain.Review;
import com.example.sparta.shared.Enum.Age;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MyPageResponseDto {
    private Long id;
    private String email;
    private String name;
    private Age age;
    private List<Review> reviews;

    @Builder
    private MyPageResponseDto(
            Long id,
            String email,
            String name,
            Age age,
            List<Review> reviews
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
        this.reviews = reviews;
    }
}
