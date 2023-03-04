package com.example.sparta.dto.LoginDto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;

    private String password;

    @Builder
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
