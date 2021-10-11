package com.epam.esm.validator;

import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validator for GiftCertificateDTO.
 *
 * @author Yauheni Tsitov
 */
public class GiftCertificateDTOValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    private GiftCertificateDTOValidator() {
    }

    /**
     * Validate GiftCertificateDTO.
     *
     * @param giftCertificateDTO object for validate.
     */
    public static void isGiftCertificateDTOValid(GiftCertificateDTO giftCertificateDTO) {
        StringBuilder errorMessage = new StringBuilder();
        String name = giftCertificateDTO.getName();
        String description = giftCertificateDTO.getDescription();
        BigDecimal price = giftCertificateDTO.getPrice();
        Integer duration = giftCertificateDTO.getDuration();
        List<Tag> tagList = giftCertificateDTO.getTags();
        if (name != null && !isNameValid(name)) {
            errorMessage.append("Gift certificate name is not valid");
        }
        if (description != null && !isDescriptionValid(description)) {
            errorMessage.append("Gift certificate name is not valid");
        }
        if (price != null && !isPriceValid(price)) {
            errorMessage.append("Gift certificate price is not valid");
        }
        if (duration != null && !isDurationValid(duration)) {
            errorMessage.append("Gift certificate duration is not valid");
        }
        if (tagList != null) {
            for (Tag tag : tagList) {
                String tagName = tag.getName();
                if (!TagValidator.isNameValid(tagName)) {
                    errorMessage.append("Tag name").append(tagName).append("is not valid");
                }
            }
        }
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    private static boolean isNameValid(String name) {
        return NAME_REGEX.matcher(name).matches();
    }

    private static boolean isDescriptionValid(String description) {
        return !description.isEmpty() && description.length() < 1000;
    }

    private static boolean isPriceValid(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }

    private static boolean isDurationValid(Integer duration) {
        return duration.compareTo(0) > 0;
    }
}
