package com.example.sparta.repository;

import com.example.sparta.domain.Review;
import com.example.sparta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByIdAndUser(Long reviewId, User user);

    List<Review> findAllByProduct(Long productId);
}
