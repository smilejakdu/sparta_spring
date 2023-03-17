package com.example.sparta.dto.ProductDto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateProductResponseDto {
    private Long id;

    private String title;

    private int price;

    private String image;

    @Builder
    public UpdateProductResponseDto(
            Long id,
            String title,
            String image
    ) {
        this.id = id;
        this.title = title;
        this.image = image;
    }
}
