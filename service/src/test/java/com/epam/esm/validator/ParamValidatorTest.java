package com.epam.esm.validator;

import com.epam.esm.dto.ParamContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParamValidatorTest {
    private final ParamValidator paramValidator = new ParamValidator();

    @Test
    void isParamValid() {
        ParamContainer paramContainer = new ParamContainer();
        assertDoesNotThrow(() -> paramValidator.isParamValid(paramContainer));
    }
}