package com.example.sparta.dto.ReviewDto.ResponseDto;

import com.example.sparta.domain.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GetReviewListByUserResponseDto {

    private List<Review> reviewList;

    @Builder
    public GetReviewListByUserResponseDto(
            List<Review> reviewList
    ) {
        this.reviewList = reviewList;
    }
}
