package com.epam.esm.converter;

import com.epam.esm.entity.user.Permission;
import com.epam.esm.entity.user.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorityConverter {
    public Set<SimpleGrantedAuthority> convert(UserRole userRole) {
        Set<Permission> permissions = userRole.getPermissions();
        return permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermissionName()))
                .collect(Collectors.toSet());
    }
}

