package com.epam.esm.entity.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Roles of a user.
 *
 * @author Yauheni Tsitou
 */
public enum UserRole {
    USER(Arrays.asList(Permission.USERS_READ, Permission.ORDERS_CREATE)),
    ADMIN(Arrays.asList(Permission.values()));
    private final Set<Permission> permissions;

    UserRole(Collection<Permission> collection) {
        this.permissions = new HashSet<>(collection);
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
    }
}
