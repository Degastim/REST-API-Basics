package com.epam.esm.validator;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidURLParameterException;
import org.springframework.stereotype.Component;

/**
 * Validator for pagination container.
 *
 * @author Yauheni Tsitov
 */
@Component
public class PaginationContainerValidator {
    /**
     * Is PaginationContainer valid.
     *
     * @param paginationContainer the object for validation.
     */
    public void paginationPaginationContainer(PaginationContainer paginationContainer) {
        int page = paginationContainer.getPage();
        int size = paginationContainer.getSize();
        if ((page == 0 && size != 0)) {
            throw new InvalidURLParameterException("page=0 and size!=0 is invalid.", ExceptionCauseCode.UNKNOWN);
        }
        if (size == 0 && page != 0) {
            throw new InvalidURLParameterException("size=0 and page!=0 is invalid.", ExceptionCauseCode.UNKNOWN);
        }
        if (page == 0 && size == 0) {
            paginationContainer.setPage(1);
            paginationContainer.setSize(10);
        }
    }
}
