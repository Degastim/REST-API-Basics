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
    private final int BEARER_INDENT = 7;
    @Value("${jwt.secret:secret}")
    private String secretKey;
    @Value("${jwt.expiration:604800}")
    private long validityMilliSeconds;
    @Value("${jwt.header:Authorization}")
    private String authorizationHeader;

    @Autowired
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Create JWT by username and role.
     *
     * @author Yauheni Tstiov
     */
    public String createToken(String username, String role, long id) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", id);
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
     * Return user id from jwt in request
     *
     * @param token
     * @ user id from token
     */
    public long getUserId(String token) {
        Number number = (Number) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("id");
        return number.longValue();
    }

    /**
     * Create JWT by username and role.
     *
     * @param token contains token for get authentication
     * @return Authentication
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
        String bearerToken = request.getHeader(authorizationHeader);
        if (bearerToken != null) {
            return bearerToken.substring(BEARER_INDENT);
        } else {
            return null;
        }
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
