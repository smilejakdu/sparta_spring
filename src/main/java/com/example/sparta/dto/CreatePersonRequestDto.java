package com.example.sparta.dto;

import com.example.sparta.shared.Enum.Age;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePersonRequestDto {
    private String name;

    private String email;

    private String password;

    private Age age;
}
