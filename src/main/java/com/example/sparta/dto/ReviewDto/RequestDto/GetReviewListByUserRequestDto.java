package com.example.sparta.dto.ReviewDto.RequestDto;

import com.example.sparta.domain.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GetReviewListByUserRequestDto {
    private List<Review> reviewList;

    @Builder
    public GetReviewListByUserRequestDto(
            List<Review> reviewList
    ) {
        this.reviewList = reviewList;
    }
}
