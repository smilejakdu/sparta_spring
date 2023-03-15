package com.example.sparta.controller;

import com.example.sparta.dto.CreatePersonRequestDto;
import com.example.sparta.domain.User;
import com.example.sparta.dto.LoginDto.LoginRequestDto;
import com.example.sparta.dto.LoginDto.LoginResponseDto;
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
    public User createPerson(
            @RequestBody CreatePersonRequestDto requestDto
    ) {
        return userService.createPerson(requestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletResponse response
    ) {
        return userService.login(requestDto, response);
    }

    @GetMapping("/mypage")
    public User getMyPage(
            @RequestHeader("Authorization") String jwtToken
    ) {
        System.out.println("jwtToken = " + jwtToken);
        return userService.getMyPage(jwtToken);
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
