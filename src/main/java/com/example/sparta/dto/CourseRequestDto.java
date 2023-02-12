package com.example.sparta.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CourseRequestDto {
    private final String title;
    private final String tutor;
}
