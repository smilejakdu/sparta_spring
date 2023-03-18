package com.example.sparta.controller;

import com.example.sparta.domain.User;
import com.example.sparta.dto.ReplyDto.CreateReplyRequestDto;
import com.example.sparta.dto.ReplyDto.CreateReplyResponseDto;
import com.example.sparta.service.ReplyService;
import com.example.sparta.service.UserService;
import com.example.sparta.shared.SwaggerConfig;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = SwaggerConfig.REPLY)
@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
@RequestMapping("/api/reply")
public class ReplyController {
    private final ReplyService replyService;
    private final UserService userService;

    @PostMapping()
    public CreateReplyResponseDto createReply(
            @RequestBody CreateReplyRequestDto requestDto,
            @RequestHeader("Authorization") String jwtToken
    ) {
        User foundUser = userService.getMyPage(jwtToken);
        return replyService.createReply(foundUser, requestDto);
    }
}
