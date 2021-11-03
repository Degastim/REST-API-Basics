package com.epam.esm.dao.constant.sql;

/**
 * Class with the tag sql.
 *
 * @author Yauheni Tstiov
 */
public class TagSql {
    private TagSql() {
    }

    public static final String FIND_ALL_WITH_LIMIT = "SELECT tags.* FROM tags LIMIT ?,?";
    public static final String FIND_ALL = "SELECT tags.* FROM tags";
    public static final String FIND_BY_NAME = "SELECT tags.* FROM tags WHERE tag_name=?";
    public static final String FIND_MOST_WIDELY_TAG_USERS_HIGHEST_COST_ORDERS = "SELECT tags.* FROM users LEFT JOIN orders ON users.user_id=orders.user_id LEFT JOIN gift_certificates ON gift_certificates.gift_certificate_id=orders.order_gift_certificate_id LEFT JOIN gift_certificates_tags ON gift_certificates_tags.gift_certificate_id=gift_certificates.gift_certificate_id LEFT JOIN tags ON tags.tag_id=gift_certificates_tags.tag_id WHERE users.user_id= (SELECT user_id FROM orders GROUP BY user_id ORDER BY SUM(price) DESC LIMIT 1) group by tag_id ORDER BY COUNT(tags.tag_id) DESC LIMIT 1";
}
