package com.example.sparta.service;

import com.example.sparta.domain.Product;
import com.example.sparta.dto.ItemDto;
import com.example.sparta.dto.ProductMypriceRequestDto;
import com.example.sparta.dto.ProductRequestDto;
import com.example.sparta.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product createProduct(ProductRequestDto requestDto) {
        Product product = Product.builder()
                .title(requestDto.getTitle())
                .lprice(requestDto.getLprice())
                .link(requestDto.getLink())
                .image(requestDto.getImage())
                .build();
        return productRepository.save(product);
    }

    @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        product.setMyprice(requestDto.getMyprice());
        productRepository.save(product);
        return id;
    }

    @Transactional
    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
        return id;
    }

    @Transactional
    public void updateBySearch(Long id, ItemDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        product.setTitle(requestDto.getTitle());
        product.setImage(requestDto.getImage());
        product.setLink(requestDto.getLink());
        product.setLprice(requestDto.getLprice());
        productRepository.save(product);
    }
}
