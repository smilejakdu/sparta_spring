package com.example.sparta.controller;


import com.example.sparta.domain.Person;
import com.example.sparta.dto.ReviewDto.CreateReviewRequestDto;
import com.example.sparta.dto.ReviewDto.CreateReviewResponseDto;
import com.example.sparta.service.PersonService;
import com.example.sparta.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
@CrossOrigin(origins = "http://localhost:3000") // CORS를 허용합니다.
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final PersonService personService;

    @PostMapping()
    public CreateReviewResponseDto createReview(
             @RequestBody CreateReviewRequestDto requestDto,
             @RequestHeader("Authorization") String jwtToken
    ) {
        Person foundPerson = personService.getMyPage(jwtToken);
        return reviewService.createReview(foundPerson,requestDto);
    }
}
