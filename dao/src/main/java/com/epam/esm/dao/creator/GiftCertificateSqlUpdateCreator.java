package com.epam.esm.dao.creator;

/**
 * The Sql update query creator.
 *
 * @author Yauheni Tstiov
 */
public class GiftCertificateSqlUpdateCreator {
    private final StringBuilder sql = new StringBuilder("UPDATE gift_certificates SET");
    private boolean paramExist = false;
    private boolean hasCondition = false;
    private static final String SPACE_SYMBOL = " ";
    private static final String APOSTROPHE_SYMBOL = "'";
    private static final String COMMA_SYMBOL = ",";
    private static final String EQUAL_SIGN = "=";
    private static final String AND_SQL = "AND";
    private static final String WHERE_SQL = "WHERE";

    /**
     * Add WHERE clause  with LIKE operator.
     *
     * @param column contain column for update.
     * @param param  contain value which will be equal to column.
     */
    public void addParameter(String column, String param) {
        if (paramExist) {
            sql.append(COMMA_SYMBOL);
        } else {
            paramExist = true;
        }
        sql.append(SPACE_SYMBOL).append(column).append(EQUAL_SIGN).append(APOSTROPHE_SYMBOL).append(param)
                .append(APOSTROPHE_SYMBOL);
    }

    /**
     * Add WHERE clause  with =
     *
     * @param param contain param for WHERE
     * @param value contain value which is searched for in WHERE
     */
    public void addWhereEquality(String param, String value) {
        addWhere();
        sql.append(param).append(EQUAL_SIGN).append(APOSTROPHE_SYMBOL).append(value).append(APOSTROPHE_SYMBOL)
                .append(SPACE_SYMBOL);
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
