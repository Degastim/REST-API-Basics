package com.epam.esm.security;

import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Provider of javascript web token.
 *
 * @author Yauheni Tstiov
 */
@Component
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Value("${jwt.secret:secret}")
    private String secretKey;
    @Value("${jwt.expiration:604800}")
    private long validityMilliSeconds;
    @Value("${jwt.header:Authorization}")
    private String authorizationHeader;

    /**
     * Create JWT by username and role.
     *
     * @author Yauheni Tstiov
     */
    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityMilliSeconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    /**
     * Validate JWT by expiration.
     *
     * @author Yauheni Tstiov
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Jwt is not valid", ExceptionCauseCode.UNKNOWN);
        }
    }
    /**
     * Create JWT by username and role.
     *
     * @author Yauheni Tstiov
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    /**
     * Create JWT by username and role.
     *
     * @author Yauheni Tstiov
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }
    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
