package com.epam.esm.validator;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.exception.InvalidURLParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PaginationContainerValidatorTest {
    private final PaginationContainerValidator paginationContainerValidator = new PaginationContainerValidator();

    @Test
    void isPaginationContainerValid() {
        assertThrows(InvalidURLParameterException.class,
                () -> paginationContainerValidator.isPaginationContainerValid(new PaginationContainer(3, 0)));
    }
}