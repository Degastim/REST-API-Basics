package com.epam.esm.dao.constant.sql;

/**
 * Class with the orders sql.
 *
 * @author Yauheni Tstiov
 */
public class OrderSql {
    private OrderSql() {
    }

    public static final String FIND_ALL_BY_USER_ID = "SELECT * FROM orders WHERE user_id=?";
    public static final String FIND_ALL_BY_USER_ID_AND_ORDER_ID = "SELECT * FROM orders WHERE user_id=? AND order_id=?";
    public static final String FIND_ALL_BY_USER_ID_AND_LIMIT = "SELECT * FROM orders WHERE user_id=? LIMIT ?,?";
}
