package com.example.sparta.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateProductRequestDto {

    private String title;
    private String image;
    private String link;
    private int price;

    @Builder
    public UpdateProductRequestDto(
            String title,
            String image,
            int price
    ) {
        this.title = title;
        this.image = image;
        this.price = price;
    }
}
