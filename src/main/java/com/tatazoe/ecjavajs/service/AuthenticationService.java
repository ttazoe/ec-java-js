package com.tatazoe.ecjavajs.service;

import com.tatazoe.ecjavajs.config.MessageStrings;
import com.tatazoe.ecjavajs.exception.AuthenticationFailException;
import com.tatazoe.ecjavajs.model.AuthenticationToken;
import com.tatazoe.ecjavajs.model.User;
import com.tatazoe.ecjavajs.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if (Objects.nonNull(authenticationToken)) {
            return authenticationToken.getUser();
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }
}
