package com.epam.esm.validator;

import java.util.regex.Pattern;

/**
 * Validates tag.
 *
 * @author Yauheni Tsitov
 */
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
    public static boolean isNameValid(String name) {
        return NAME_REGEX.matcher(name).matches();
    }
}

