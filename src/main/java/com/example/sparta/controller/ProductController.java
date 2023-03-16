package com.example.sparta.controller;

import com.example.sparta.domain.Product;
import com.example.sparta.dto.ProductMypriceRequestDto;
import com.example.sparta.dto.ProductRequestDto;
import com.example.sparta.service.ProductService;
import com.example.sparta.shared.SwaggerConfig;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = SwaggerConfig.PRODUCT)
@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
@CrossOrigin(origins = "http://localhost:3000") // CORS를 허용합니다.
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // 등록된 전체 상품 목록 조회
    @GetMapping()
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    // 신규 상품 등록
    @PostMapping()
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @PutMapping("/{id}")
    public Long updateProduct(
            @PathVariable Long id,
            @RequestBody ProductMypriceRequestDto requestDto
    ) {
        return productService.updateProduct(id, requestDto);
    }
}
