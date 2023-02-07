package com.example.sparta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 생성일자와 수정일짜를 자동으로 업데이트하게된다.
@SpringBootApplication
public class SpartaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaApplication.class, args);
    }
}
