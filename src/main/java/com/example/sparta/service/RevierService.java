package com.example.sparta.service;

import com.example.sparta.domain.Review;
import com.example.sparta.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RevierService {

    private final ReviewRepository reviewRepository;
}
