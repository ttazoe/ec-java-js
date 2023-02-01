package com.tatazoe.ecjavajs.repository;

import com.tatazoe.ecjavajs.model.AuthenticationToken;
import com.tatazoe.ecjavajs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
