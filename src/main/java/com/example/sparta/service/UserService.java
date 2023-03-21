package com.example.sparta.service;

import com.example.sparta.domain.User;
import com.example.sparta.dto.CreatePersonRequestDto;
import com.example.sparta.dto.LoginDto.LoginRequestDto;
import com.example.sparta.dto.LoginDto.LoginResponseDto;
import com.example.sparta.dto.UpdatePersonRequestDto;
import com.example.sparta.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<User> getPeople() {
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(
            CreatePersonRequestDto requestDto
    ) {
        String email = requestDto.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        String hashedPassword = passwordEncoder.encode(requestDto.getPassword());

        User person = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .age(requestDto.getAge())
                .password(hashedPassword)
                .build();

        return userRepository.save(person);
    }

    @Transactional
    public LoginResponseDto login(
            LoginRequestDto requestDto,
            HttpServletResponse response
    ) {
        User foundUser = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 person이 존재하지 않습니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), foundUser.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        System.out.println("person.getId():" + foundUser.getId());

        JwtService jwtService = new JwtService();
        String jwtToken = jwtService.createToken(foundUser.getId());
        System.out.println("jwtToken:" + jwtToken);
        Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return LoginResponseDto.builder()
                .email(foundUser.getEmail())
                .token(jwtToken)
                .message("SUCCESS")
                .build();
    }

    @Transactional
    public User getMyPage(
            String jwtToken
    ) {
        JwtService jwtService = new JwtService();
        Long userId = jwtService.getUserIdFromToken(jwtToken);
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 person이 존재하지 않습니다.")
        );
    }

    @Transactional
    public User updateUser(
            Long id,
            UpdatePersonRequestDto requestDto
    ) {
        User person = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 person이 존재하지 않습니다.")
        );

        person.setName(requestDto.getName());
        person.setEmail(requestDto.getEmail());

        return person;
    }

    @Transactional
    public Long deletePerson(Long id) {
        User person = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 person이 존재하지 않습니다.")
        );

        userRepository.delete(person);
        return id;
    }
}
