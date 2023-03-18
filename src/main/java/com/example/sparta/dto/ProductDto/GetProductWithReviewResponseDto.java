package com.example.sparta.dto.ProductDto;

import com.example.sparta.dto.ReviewDto.GetUserIdAndEmailResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GetProductWithReviewResponseDto {
    private Long id;

    private String title;

    private String image;

    private String link;

    private int price;

    private List<GetUserIdAndEmailResponseDto> reviews;

    @Builder
    public GetProductWithReviewResponseDto(
            Long id,
            String title,
            String image,
            String link,
            int price,
            List<GetUserIdAndEmailResponseDto> reviews
    ) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.link = link;
        this.price = price;
        this.reviews = reviews;
    }
}
