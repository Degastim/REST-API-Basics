package com.epam.esm.dao.constant.sql;

/**
 * Class with the orders sql.
 *
 * @author Yauheni Tstiov
 */
public class OrderSql {
    private OrderSql() {
    }

    public static final String FIND_ALL_BY_USER_ID = "Select * FROM orders WHERE user_id=?";
}
