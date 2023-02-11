package com.example.sparta.repository;

import com.example.sparta.memo.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByModifiedAtBeforeOrderByModifiedAtDesc(
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}
