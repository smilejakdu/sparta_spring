package com.example.sparta.repository;

import com.example.sparta.domain.Product;
import com.example.sparta.dto.ProductDto.GetProductWithScoreResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.id, p.title, p.image, p.link, p.price, ROUND(AVG(r.score), 1) FROM Product p LEFT JOIN Review r ON p.id = r.product.id GROUP BY p.id")
    List<GetProductWithScoreResponseDto> findProductWithScore();
}