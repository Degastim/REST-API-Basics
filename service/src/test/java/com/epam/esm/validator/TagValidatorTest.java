package com.epam.esm.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TagValidatorTest {
    private static final String TAG_NAME_FALSE = "123";
    private static final String TAG_NAME_TRUE = "ар";

    @Test
    void isNameValidFalse() {
        boolean actual = TagValidator.isNameValid(TAG_NAME_FALSE);
        assertFalse(actual);
    }

    @Test
    void isNameValidTrue() {
        boolean actual = TagValidator.isNameValid(TAG_NAME_TRUE);
        assertTrue(actual);
    }
}