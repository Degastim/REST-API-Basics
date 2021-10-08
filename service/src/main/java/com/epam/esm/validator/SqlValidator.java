package com.epam.esm.validator;

/**
 * Validates sql constant.
 *
 * @author Yauheni Tsitov
 */
public class SqlValidator {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    private SqlValidator() {
    }

    /**
     * Is order by param vald.
     *
     * @param sortParam the param of sort
     * @return the boolean
     */
    public static boolean ORDER_BY(String sortParam) {
        return sortParam.equalsIgnoreCase(ASC) || sortParam.equalsIgnoreCase(DESC);
    }
}
