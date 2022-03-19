package com.epam.esm.filter;

import com.epam.esm.entity.ResponseExceptionEntity;
import com.epam.esm.exception.JwtAuthenticationException;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.security.JwtTokenProvider;
import com.epam.esm.util.ErrorCodeCounter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to check javascript web token.
 *
 * @author Yauheni Tstiov
 */
@Component
public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (JwtAuthenticationException e) {
            exceptionHandling((HttpServletResponse) servletResponse, e.getMessage(), e.getCodeExceptionCause());
        } catch (ResourceNotFoundedException e) {
            exceptionHandling((HttpServletResponse) servletResponse, e.getMessage(), e.getCodeExceptionCause());
        }
    }

    private void exceptionHandling(HttpServletResponse servletResponse, String message, int codeExceptionCause) throws IOException {
        SecurityContextHolder.clearContext();
        servletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        servletResponse.setContentType("application/json");
        codeExceptionCause = ErrorCodeCounter.countErrorCode(HttpStatus.BAD_REQUEST, codeExceptionCause);
        ResponseExceptionEntity entity = new ResponseExceptionEntity(message, codeExceptionCause);
        ObjectMapper objectMapper = new ObjectMapper();
        servletResponse.getOutputStream().print(objectMapper.writeValueAsString(entity));
    }
}
