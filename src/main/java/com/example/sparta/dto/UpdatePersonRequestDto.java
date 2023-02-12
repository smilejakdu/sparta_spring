package com.example.sparta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePersonRequestDto {
    private final Long id;

    private final String name;

    private final String email;
}
