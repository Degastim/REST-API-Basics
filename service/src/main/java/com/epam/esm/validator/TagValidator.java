package com.epam.esm.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Validates tag.
 *
 * @author Yauheni Tsitov
 */
@Component
public class TagValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    private TagValidator() {
    }

    /**
     * Is name valid.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean isTagNameValid(String name) {
        return NAME_REGEX.matcher(name).matches();
    }
}

