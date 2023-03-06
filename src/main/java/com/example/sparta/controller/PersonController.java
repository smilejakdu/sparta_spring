package com.example.sparta.controller;

import com.example.sparta.dto.CreatePersonRequestDto;
import com.example.sparta.domain.Person;
import com.example.sparta.dto.LoginDto.LoginRequestDto;
import com.example.sparta.dto.LoginDto.LoginResponseDto;
import com.example.sparta.dto.UpdatePersonRequestDto;
import com.example.sparta.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    @GetMapping()
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @PostMapping()
    public Person createPerson(
            @RequestBody CreatePersonRequestDto requestDto
    ) {
        return personService.createPerson(requestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletResponse response
    ) {
        return personService.login(requestDto, response);
    }

    @GetMapping("/mypage")
    public Person getMyPage(
            @RequestHeader("Authorization") String jwtToken
    ) {
        System.out.println("jwtToken = " + jwtToken);
        return personService.getMyPage(jwtToken);
    }

    @PutMapping("/{id}")
    public Person updatePerson(
            @PathVariable Long id,
            @RequestBody UpdatePersonRequestDto requestDto
    ) {
        return personService.updatePerson(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }
}
