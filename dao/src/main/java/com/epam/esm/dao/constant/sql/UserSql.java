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
}
