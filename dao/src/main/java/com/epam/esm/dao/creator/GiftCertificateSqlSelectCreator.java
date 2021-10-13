package com.epam.esm.dao.creator;

/**
 * The Sql select query creator.
 *
 * @author Yauheni Tstiov
 */
public class GiftCertificateSqlSelectCreator {
    private final StringBuilder sql = new StringBuilder("SELECT gift_certificates.*,tags.* FROM gift_certificates ")
            .append("LEFT JOIN gift_certificates_tags ")
            .append("ON gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id ")
            .append("LEFT JOIN tags ON gift_certificates_tags.tag_id=tags.tag_id");
    private boolean hasCondition = false;
    private boolean hasOrderBy = false;
    private static final String LIKE_SQL = "LIKE";
    private static final String SPACE_SYMBOL = " ";
    private static final String APOSTROPHE_SYMBOL = "'";
    private static final String COMMA_SYMBOL = ",";
    private static final String PERCENTAGE_SYMBOL = "%";
    private static final String EQUAL_SIGN = "=";
    private static final String ORDER_BY_SQL = "ORDER BY";
    private static final String AND_SQL = "AND";
    private static final String WHERE_SQL = "WHERE";

    /**
     * Add WHERE clause  with LIKE operator
     *
     * @param param contain param for WHERE
     * @param value contain value which is searched for in WHERE
     */
    public void addWhereLike(String param, String value) {
        addWhere();
        sql.append(param).append(SPACE_SYMBOL).append(LIKE_SQL).append(SPACE_SYMBOL).append(APOSTROPHE_SYMBOL)
                .append(PERCENTAGE_SYMBOL).append(value).append(PERCENTAGE_SYMBOL).append(APOSTROPHE_SYMBOL);
    }

    /**
     * Add WHERE clause  with =
     *
     * @param param contain param for WHERE
     * @param value contain value which is searched for in WHERE
     */
    public void addWhereEquality(String param, String value) {
        addWhere();
        sql.append(param).append(EQUAL_SIGN).append(APOSTROPHE_SYMBOL).append(value).append(APOSTROPHE_SYMBOL);
    }

    /**
     * Add ORDER BY clause
     *
     * @param param    contain param for ORDER BY
     * @param typeSort contain value how will it be sorted
     */
    public void addOrderBy(String param, String typeSort) {
        if (hasOrderBy) {
            sql.append(COMMA_SYMBOL);
        } else {
            sql.append(SPACE_SYMBOL).append(ORDER_BY_SQL).append(SPACE_SYMBOL);
            hasOrderBy = true;
        }
        sql.append(param).append(SPACE_SYMBOL).append(typeSort);
    }

    /**
     * Add WHERE clause  with =
     */
    private void addWhere() {
        if (hasCondition) {
            sql.append(SPACE_SYMBOL).append(AND_SQL).append(SPACE_SYMBOL);
        } else {
            sql.append(SPACE_SYMBOL).append(WHERE_SQL).append(SPACE_SYMBOL);
            hasCondition = true;
        }
    }

    /**
     * Returns the collected sql request
     *
     * @return StringBuild sql for execute
     */
    public StringBuilder getSql() {
        return sql;
    }
}