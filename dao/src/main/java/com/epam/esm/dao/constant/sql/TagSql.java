package com.epam.esm.dao.constant.sql;

/**
 * Class with the tag sql.
 *
 * @author Yauheni Tstiov
 */
public class TagSql {
    private TagSql() {
    }

    public static final String ADD_TAG = "INSERT INTO tags(tag_name) VALUES(?)";
    public static final String FIND_ALL = "SELECT tag_id, tag_name FROM tags";
    public static final String FIND_BY_NAME = "SELECT tag_id, tag_name FROM tags WHERE tag_name=?";
    public static final String FIND_BY_ID = "SELECT tag_id, tag_name FROM tags WHERE tag_id=?";
    public static final String DELETE_TAG = "DELETE FROM tags WHERE tag_id=?";
    public static final String DELETE_BY_TAG_ID = "DELETE FROM gift_certificates_tags WHERE tag_id=?";
    public static final String UPDATE_BY_ID = "UPDATE tags SET tag_name=? WHERE tag_id=?";
    public static final String FIND_BY_ID_AND_NAME = "SELECT tag_id, tag_name FROM tags WHERE tag_id=? AND tag_name=?";
    public static final String ADD_TAG_WITH_ID = "INSERT INTO tags(tag_id,tag_name) VALUES(?,?)";
}
