package com.tatazoe.ecjavajs.controller;

import com.tatazoe.ecjavajs.dto.SignInDto;
import com.tatazoe.ecjavajs.dto.SignInResponseDto;
import com.tatazoe.ecjavajs.dto.SignUpResponseDto;
import com.tatazoe.ecjavajs.dto.SignupDto;
import com.tatazoe.ecjavajs.exception.AuthenticationFailException;
import com.tatazoe.ecjavajs.exception.CustomException;
import com.tatazoe.ecjavajs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.singUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto SignIn(@RequestBody SignInDto signInDto) throws AuthenticationFailException,CustomException{
        return userService.signIn(signInDto);
    }
}
