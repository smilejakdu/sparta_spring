package com.example.sparta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePersonRequestDto {
    private String name;
    private String email;
}
