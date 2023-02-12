package com.example.sparta.shared;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Component
@NoArgsConstructor
public class NaverShopSearch {

    @Value("${naver.client.id}")
    private String NaverClientId;

    @Value("${naver.client.secret}")
    private String NaverClientSecret;

    public String search() {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Naver-Client-Id", this.NaverClientId);
        headers.add("X-Naver-Client-Secret", this.NaverClientSecret);
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://openapi.naver.com/v1/search/shop.json?query=갤럭시",
                GET,
                requestEntity,
                String.class
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int statusCode = httpStatus.value();
        String responseBody = responseEntity.getBody();
        System.out.println("status code: " + statusCode);
        System.out.println("response body: " + responseBody);
        return responseBody;
    }
}