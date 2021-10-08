package com.epam.esm.validator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Validates gift certificate.
 *
 * @author Yauheni Tsitov
 */
public class GiftCertificateValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    private GiftCertificateValidator() {
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

    /**
     * Is description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isDescriptionValid(String description) {
        return description != null && !description.isEmpty() && description.length() < 1000;
    }

    /**
     * Is price valid boolean.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean isPriceValid(BigDecimal price) {
        return (price != null && price.compareTo(BigDecimal.ZERO) > 0);
    }

    /**
     * Is duration valid boolean.
     *
     * @param duration the duration
     * @return the boolean
     */
    public static boolean isDurationValid(int duration) {
        return duration > 0;
    }

    /**
     * Is createDate valid boolean.
     *
     * @param createDate the createDate
     * @return the boolean
     */
    public static boolean isCreateDateValid(LocalDateTime createDate) {
        return createDate != null;
    }

    /**
     * Is lastUpdateDate valid boolean.
     *
     * @param lastUpdateDate the lastUpdateDate
     * @return the boolean
     */
    public static boolean isLastUpdateDateValid(LocalDateTime lastUpdateDate) {
        return lastUpdateDate != null;
    }
}
