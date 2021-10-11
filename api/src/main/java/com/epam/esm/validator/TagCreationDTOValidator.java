package com.epam.esm.validator;

import com.epam.esm.dto.tag.TagCreationDTO;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;

import java.util.regex.Pattern;

/**
 * Validates TagCreationDTO.
 *
 * @author Yauheni Tsitov
 */
public class TagCreationDTOValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    private TagCreationDTOValidator() {
    }

    /**
     * Is name TagCreationDTO valid.
     *
     * @param tagCreationDTO the object for validation.
     */
    public static void isTagCreationDTOValid(TagCreationDTO tagCreationDTO) {
        if (!NAME_REGEX.matcher(tagCreationDTO.getName()).matches()) {
            throw new InvalidFieldValueException("Tag name is not valid", ExceptionCauseCode.TAG);
        }
    }
}
