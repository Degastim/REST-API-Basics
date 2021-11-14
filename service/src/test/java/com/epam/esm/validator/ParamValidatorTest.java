package com.epam.esm.validator;

import com.epam.esm.dto.param.ParamContainer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParamValidatorTest {
    private final ParamValidator paramValidator = new ParamValidator();

    @Test
    void isParamValid() {
        ParamContainer paramContainer = new ParamContainer(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertDoesNotThrow(() -> paramValidator.isParamValid(paramContainer));
    }
}