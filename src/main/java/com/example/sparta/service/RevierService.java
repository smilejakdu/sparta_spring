package com.example.sparta.service;

import com.example.sparta.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RevierService {

    private final ReviewRepository reviewRepository;
}
