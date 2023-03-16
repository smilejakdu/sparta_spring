package com.example.sparta.domain;


import com.example.sparta.shared.Timestamped;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Reply extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Review review;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Reply(
            User user,
            Review review,
            String content
    ) {
        this.user = user;
        this.review = review;
        this.content = content;
    }
}
