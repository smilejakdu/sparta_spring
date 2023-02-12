package com.example.sparta.service;

import com.example.sparta.dto.CreatePersonRequestDto;
import com.example.sparta.domain.Person;
import com.example.sparta.dto.UpdatePersonRequestDto;
import com.example.sparta.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional
    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @Transactional
    public Person createPerson(
            CreatePersonRequestDto requestDto
    ) {
        Person person = new Person();
        person.setName(requestDto.getName());
        person.setEmail(requestDto.getEmail());

        return personRepository.save(person);
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
