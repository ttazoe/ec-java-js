package com.tatazoe.ecjavajs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignInResponseDto {
    private String status;
    private String token;
}
