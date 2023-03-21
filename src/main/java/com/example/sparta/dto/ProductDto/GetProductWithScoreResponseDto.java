package com.example.sparta.dto.ProductDto;

import lombok.Builder;
import lombok.Data;

@Data
public class GetProductWithScoreResponseDto {
    private Long id;
    private String title;
    private String image;
    private String link;
    private int price;
    private int score;

    @Builder
    public GetProductWithScoreResponseDto(
            Long id,
            String title,
            String image,
            String link,
            int price,
            int score
    ) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.link = link;
        this.price = price;
        this.score = score;
    }
}
