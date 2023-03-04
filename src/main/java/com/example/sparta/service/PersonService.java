package com.example.sparta.service;

import com.example.sparta.dto.CreatePersonRequestDto;
import com.example.sparta.domain.Person;
import com.example.sparta.dto.LoginDto.LoginRequestDto;
import com.example.sparta.dto.UpdatePersonRequestDto;
import com.example.sparta.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @Transactional
    public Person createPerson(
            CreatePersonRequestDto requestDto
    ) {
        String email = requestDto.getEmail();
        if (personRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        String hashedPassword = passwordEncoder.encode(requestDto.getPassword());

        Person person = Person.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(hashedPassword)
                .build();

        return personRepository.save(person);
    }

    @Transactional
    public String login(
            LoginRequestDto requestDto
    ) {
        Person person = personRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 person이 존재하지 않습니다."));

        JwtService jwtService = new JwtService();
        return jwtService.createToken(person.getId());
    }

    @Transactional
    public Person updatePerson(
            Long id,
            UpdatePersonRequestDto requestDto
    ) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 person이 존재하지 않습니다.")
        );

        person.setName(requestDto.getName());
        person.setEmail(requestDto.getEmail());

        return person;
    }

    @Transactional
    public Long deletePerson(Long id) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 person이 존재하지 않습니다.")
        );

        personRepository.delete(person);
        return id;
    }
}
