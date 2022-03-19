package com.epam.esm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration for password encoder.
 *
 * @author Yauheni Tstiov
 */
@Configuration
public class ProjectPasswordEncoder {
    /**
     * Password encoder bean.
     *
     * @author Yauheni Tstiov
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
