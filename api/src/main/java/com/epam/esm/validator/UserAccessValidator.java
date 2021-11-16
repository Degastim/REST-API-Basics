package com.epam.esm.validator;

import com.epam.esm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserAccessValidator {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserAccessValidator(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public boolean isUserValid(long userId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        long jwtUserId = jwtTokenProvider.getUserId(token);
        return userId == jwtUserId;
    }
}
