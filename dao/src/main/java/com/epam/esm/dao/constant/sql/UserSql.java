package com.epam.esm.dao.constant.sql;

/**
 * Class with the user sql.
 *
 * @author Yauheni Tstiov
 */
public class UserSql {
    private UserSql() {
    }

    public static final String FIND_ALL_WITH_LIMIT = "SELECT * FROM users LIMIT ?,?";
    public static final String FIND_ALL = "SELECT * FROM users";
    public static final String FIND_NAME = "SELECT * FROM users WHERE user_name=?";
    public static final String FIND_BY_NAME_AND_PASSWORD = "SELECT * FROM users WHERE user_name=? AND password=?";
}
