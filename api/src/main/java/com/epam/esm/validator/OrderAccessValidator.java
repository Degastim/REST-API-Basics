package com.epam.esm.validator;

import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.AccessException;
import com.epam.esm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class OrderAccessValidator {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public OrderAccessValidator(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void isUserValid(long userId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        long jwtUserId = jwtTokenProvider.getUserId(token);
        if (userId != jwtUserId) {
            throw new AccessException("You can not interact with someone else's order", ExceptionCauseCode.USER);
        }
    }
}
