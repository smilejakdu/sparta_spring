package com.example.sparta.domain;

import com.example.sparta.shared.Enum.Age;
import com.example.sparta.shared.Timestamped;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Person extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
//    enum
    @Column(name = "age", nullable = false)
    @Enumerated(EnumType.STRING)
    private Age age;

    @Builder
    public Person(String name, String password, String email, Age age) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
    }
}
