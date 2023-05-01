package com.example.sparta.controller;

import com.example.sparta.domain.User;
import com.example.sparta.dto.ReviewDto.*;
import com.example.sparta.dto.ReviewDto.RequestDto.CreateReviewRequestDto;
import com.example.sparta.dto.ReviewDto.ResponseDto.CreateReviewResponseDto;
import com.example.sparta.service.ReviewService;
import com.example.sparta.service.UserService;
import com.example.sparta.shared.SwaggerConfig;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = SwaggerConfig.REVIEW)
@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping()
    public CreateReviewResponseDto createReview(
             @RequestBody CreateReviewRequestDto requestDto,
             @RequestHeader("Authorization") String jwtToken
    ) {
        User foundPerson = userService.getMyPage(jwtToken);
        return reviewService.createReview(foundPerson, requestDto);
    }

    @PutMapping("/{id}")
    public UpdateReviewResponseDto updateReview(
            @PathVariable Long id,
            @RequestBody UpdateReviewRequestDto requestDto,
            @RequestHeader("Authorization") String jwtToken
    ) {
        User foundPerson = userService.getMyPage(jwtToken);
        return reviewService.updateReview(id, foundPerson, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteReview(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwtToken
    ) {
        User foundPerson = userService.getMyPage(jwtToken);
        return reviewService.deleteReview(id, foundPerson);
    }

    @GetMapping("/{id}")
    public GetReviewWithReplyResponseDto getReviewWithReply(
            @PathVariable Long id
    ) {
        return reviewService.getReviewWithReply(id);
    }
}
