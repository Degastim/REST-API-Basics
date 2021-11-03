package com.epam.esm.dao.constant.sql;

public class UserSql {
    private UserSql() {
    }

    public static final String FIND_ALL_WITH_LIMIT = "SELECT * FROM users LIMIT ?,?";
    public static final String FIND_ALL = "SELECT * FROM users";
}
