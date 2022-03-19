package com.epam.esm.converter;

import com.epam.esm.entity.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter {
    private final AuthorityConverter authorityConverter;

    public UserDetailsConverter(AuthorityConverter authorityConverter) {
        this.authorityConverter = authorityConverter;
    }

    public UserDetails convert(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                authorityConverter.convert(user.getUserRole()));
    }
}
