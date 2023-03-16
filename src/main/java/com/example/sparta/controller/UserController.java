package com.example.sparta.controller;

import com.example.sparta.dto.CreatePersonRequestDto;
import com.example.sparta.domain.User;
import com.example.sparta.dto.LoginDto.LoginRequestDto;
import com.example.sparta.dto.LoginDto.LoginResponseDto;
import com.example.sparta.dto.LoginDto.MyPageResponseDto;
import com.example.sparta.dto.UpdatePersonRequestDto;
import com.example.sparta.service.UserService;
import com.example.sparta.shared.SwaggerConfig;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = SwaggerConfig.USER)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<User> getPeople() {
        return userService.getPeople();
    }

    @PostMapping()
    public User createUser(
            @RequestBody CreatePersonRequestDto requestDto
    ) {
        return userService.createUser(requestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletResponse response
    ) {
        return userService.login(requestDto, response);
    }

    @GetMapping("/mypage")
    public MyPageResponseDto getMyPage(
            @RequestHeader("Authorization") String jwtToken
    ) {
        System.out.println("jwtToken = " + jwtToken);
        User user = userService.getMyPage(jwtToken);
        return MyPageResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody UpdatePersonRequestDto requestDto
    ) {
        return userService.updateUser(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deletePerson(@PathVariable Long id) {
        return userService.deletePerson(id);
    }
}
