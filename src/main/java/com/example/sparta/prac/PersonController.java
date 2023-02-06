package com.example.sparta.prac;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myinfo")
public class PersonController {

    @GetMapping("")
    public String myInfo() {
        Person person = new Person();
        return person.getName() + " " + person.getAddress() + " " + person.getJob();
    }

    @PostMapping("")
    public String myInfo(String name, String address, String job) {
        Person person = new Person();
        person.setter(name, address, job);
        return person.getName() + " " + person.getAddress() + " " + person.getJob();
    }
}
