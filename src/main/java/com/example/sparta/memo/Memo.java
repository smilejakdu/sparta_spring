package com.example.sparta.memo;

import com.example.sparta.shared.Timestamped;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Data
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
@Table(name = "memo")
public class Memo extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;
}
