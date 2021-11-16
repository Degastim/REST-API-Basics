package com.epam.esm.validator;

import com.epam.esm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Validator for user access.
 *
 * @author Yauheni Tstiov
 */
@Component
public class UserAccessValidator {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserAccessValidator(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Compare jwt user id and resource user id..
     *
     * @param userId  contains resource user id.
     * @param request contains httpServletRequest.
     */
    public boolean isUserValid(long userId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        long jwtUserId = jwtTokenProvider.getUserId(token);
        return userId == jwtUserId;
    }
}
