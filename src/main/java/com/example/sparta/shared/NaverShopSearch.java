package com.example.sparta.shared;

import com.example.sparta.dto.ItemDto;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
@NoArgsConstructor
@Service
public class NaverShopSearch {

    @Value("${naver.client.id}")
    private String NaverClientId;

    @Value("${naver.client.secret}")
    private String NaverClientSecret;

    public List<ItemDto> search(String search) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Naver-Client-Id", this.NaverClientId);
        headers.add("X-Naver-Client-Secret", this.NaverClientSecret);
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://openapi.naver.com/v1/search/shop.json?query="+ search,
                GET,
                requestEntity,
                String.class
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int statusCode = httpStatus.value();
        String responseBody = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray items = jsonObject.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(item);
            itemDtoList.add(itemDto);
        }

        System.out.println("status code: " + statusCode);
        System.out.println("response body: " + responseBody);
        return itemDtoList;
    }
}