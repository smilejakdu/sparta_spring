package com.example.sparta.dto.LoginDto;

import com.example.sparta.shared.Enum.Age;
import lombok.Builder;
import lombok.Data;

@Data
public class MyPageResponseDto {
    private Long id;
    private String email;
    private String name;
    private Age age;

    @Builder
    private MyPageResponseDto(
            Long id,
            String email,
            String name,
            Age age
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
    }
}
