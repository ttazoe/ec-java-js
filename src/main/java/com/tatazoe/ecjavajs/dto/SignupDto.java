package com.tatazoe.ecjavajs.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignupDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
