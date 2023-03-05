package com.example.sparta.dto.LoginDto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponseDto {
   private String email;

   private String token;

   private String message;

   @Builder
   private LoginResponseDto(
           String email,
           String token,
           String message
   ) {
       this.email = email;
       this.token = token;
       this.message = message;
   }
}
