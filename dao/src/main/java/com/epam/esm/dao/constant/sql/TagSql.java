package com.epam.esm.dao.constant.sql;

/**
 * Class with the tag sql.
 *
 * @author Yauheni Tstiov
 */
public class TagSql {
    private TagSql() {
    }

    public static final String FIND_ALL = "SELECT tag_id, tag_name FROM tags";
    public static final String FIND_BY_NAME = "SELECT tags.* FROM tags WHERE tag_name=?";
}
