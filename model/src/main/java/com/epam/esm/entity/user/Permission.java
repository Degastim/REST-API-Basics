package com.epam.esm.entity.user;

/**
 * Permissions of users
 *
 * @author Yauheni Tsitou
 */
public enum Permission {
    TAGS_READ("tags:read"),
    TAGS_CREATE("tags:create"),
    TAGS_DELETE("tags:delete"),
    CERTIFICATES_READ("certificates:read"),
    CERTIFICATES_CREATE("certificates:create"),
    CERTIFICATES_UPDATE("certificates:update"),
    CERTIFICATES_DELETE("certificates:delete"),
    USERS_READ("users:read"),
    USERS_CREATE("users:create"),
    USERS_READ_ALL("users:read:all"),
    ORDERS_READ("orders:read"),
    ORDERS_CREATE("orders:create"),
    ORDERS_READ_ALL("orders:read:all");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
