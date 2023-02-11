package com.example.sparta.controller;

import com.example.sparta.domain.CreatePersonRequestDto;
import com.example.sparta.domain.Person;
import com.example.sparta.domain.UpdatePersonRequestDto;
import com.example.sparta.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.web.bind.annotation.*;

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