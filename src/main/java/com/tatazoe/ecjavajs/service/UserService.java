package com.tatazoe.ecjavajs.service;

import com.tatazoe.ecjavajs.dto.SignUpResponseDto;
import com.tatazoe.ecjavajs.dto.SignupDto;
import com.tatazoe.ecjavajs.exception.CustomException;
import com.tatazoe.ecjavajs.model.AuthenticationToken;
import com.tatazoe.ecjavajs.model.User;
import com.tatazoe.ecjavajs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignUpResponseDto singUp(SignupDto signupDto) throws CustomException {
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("User already exists");
        }

        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}");
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), signupDto.getPassword());
        try {
            userRepository.save(user);
            //user created successfully
            //generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);

            // Save token to DB
            authenticationService.saveConfirmationToken(authenticationToken);
            return new SignUpResponseDto("success", "User created successfully");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }
}