package com.example.sparta.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateProductRequestDto {
    private String title;
    private String link;
    private String image;
    private int price;

    @Builder
    public CreateProductRequestDto(
            String title,
            String link,
            String image,
            int price
    ) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.price = price;
    }
}