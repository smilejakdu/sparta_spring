package com.example.sparta.service;

import com.example.sparta.domain.Product;
import com.example.sparta.dto.CreateProductRequestDto;
import com.example.sparta.dto.ProductDto.GetProductWithReviewResponseDto;
import com.example.sparta.dto.ProductDto.GetProductWithScoreResponseDto;
import com.example.sparta.dto.ProductDto.UpdateProductResponseDto;
import com.example.sparta.dto.ReviewDto.GetUserIdAndEmailResponseDto;
import com.example.sparta.dto.UpdateProductRequestDto;
import com.example.sparta.repository.ProductRepository;
import com.example.sparta.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public List<GetProductWithScoreResponseDto> getProductList() {
        return productRepository.findProductWithScore()
                .stream()
                .map(product -> GetProductWithScoreResponseDto.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .image(product.getImage())
                        .link(product.getLink())
                        .price(product.getPrice())
                        .score(product.getScore())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public GetProductWithReviewResponseDto getProduct(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        List<GetUserIdAndEmailResponseDto> foundReviewList = reviewRepository.findAllByProduct(foundProduct)
                .stream()
                .map(review -> GetUserIdAndEmailResponseDto.builder()
                        .userId(review.getUser().getId())
                        .email(review.getUser().getEmail())
                        .reviewId(review.getId())
                        .content(review.getContent())
                        .score(review.getScore())
                        .build())
                .collect(Collectors.toList());

        return GetProductWithReviewResponseDto.builder()
                .id(foundProduct.getId())
                .title(foundProduct.getTitle())
                .image(foundProduct.getImage())
                .link(foundProduct.getLink())
                .price(foundProduct.getPrice())
                .reviews(foundReviewList)
                .build();
    }

    @Transactional
    public Product createProduct(CreateProductRequestDto requestDto) {
        Product product = Product.builder()
                .title(requestDto.getTitle())
                .price(requestDto.getPrice())
                .link(requestDto.getLink())
                .image(requestDto.getImage())
                .build();
        return productRepository.save(product);
    }

    @Transactional
    public UpdateProductResponseDto updateProduct(
            Long id,
            UpdateProductRequestDto requestDto
    ) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        foundProduct.setTitle(requestDto.getTitle());
        foundProduct.setPrice(requestDto.getPrice());
        foundProduct.setImage(requestDto.getImage());
        Product savedProduct = productRepository.save(foundProduct);
        return UpdateProductResponseDto
                .builder()
                .id(savedProduct.getId())
                .title(savedProduct.getTitle())
                .image(savedProduct.getImage())
                .build();
    }

    @Transactional
    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
        return id;
    }

    @Transactional
    public void updateBySearch(Long id, CreateProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        product.setTitle(requestDto.getTitle());
        product.setImage(requestDto.getImage());
        product.setLink(requestDto.getLink());
        product.setPrice(requestDto.getPrice());
        productRepository.save(product);
    }
}
