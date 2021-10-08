package com.epam.esm.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SqlValidatorTest {
    private static final String ASC_SORT_ORDER = "ASC";

    @Test
    void ORDER_BY() {
        boolean actual = SqlValidator.ORDER_BY(ASC_SORT_ORDER);
        assertTrue(actual);
    }
}