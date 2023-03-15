package com.example.sparta.shared.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class HttpException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
}
