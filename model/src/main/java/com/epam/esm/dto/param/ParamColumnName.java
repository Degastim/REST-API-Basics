package com.epam.esm.dto.param;

/**
 * Container for the relationship between parameter name and column name.
 *
 * @author Yauheni Tsitou
 */
public enum ParamColumnName {
    TAG_NAME("tag_name"),
    GIFT_CERTIFICATE_NAME("gift_certificate_name"),
    GIFT_CERTIFICATE_DESCRIPTION("description"),
    GIFT_CERTIFICATE_CREATE_DATE("create_date"),
    GIFT_CERTIFICATE_LAST_UPDATE_DATE("last_update_date");

    ParamColumnName(String column) {
        this.column = column;
    }

    private final String column;

    public String getColumn() {
        return column;
    }
}
