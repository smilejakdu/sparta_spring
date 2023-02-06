package com.example.sparta.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
}
