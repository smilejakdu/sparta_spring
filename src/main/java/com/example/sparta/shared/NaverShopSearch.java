package com.example.sparta.shared;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NaverShopSearch {
    public String search() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "YOUR_CLIENT_ID");
        headers.add("X-Naver-Client-Secret", "YOUR_CLIENT_ID");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=갤럭시", org.springframework.http.HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int statusCode = httpStatus.value();
        String responseBody = responseEntity.getBody();
        System.out.println("status code: " + statusCode);
        System.out.println("response body: " + responseBody);
    }
}