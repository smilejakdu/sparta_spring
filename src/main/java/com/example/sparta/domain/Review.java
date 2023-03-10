package com.example.sparta.domain;

import com.example.sparta.shared.Timestamped;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Review extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "score", nullable = false)
    private int score;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Person person;

    private String createdAt;

    @Builder
    public Review(
            String content,
            int score,
            Product product,
            Person person,
            String createdAt
    ) {
        this.content = content;
        this.score = score;
        this.product = product;
        this.person = person;
        this.createdAt = createdAt;
    }
}
