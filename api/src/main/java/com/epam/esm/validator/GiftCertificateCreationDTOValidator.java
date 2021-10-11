package com.epam.esm.validator;

import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validator for GiftCertificateCreationDTO.
 *
 * @author Yauheni Tsitov
 */
public class GiftCertificateCreationDTOValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    public GiftCertificateCreationDTOValidator() {
    }

    /**
     * Validate GiftCertificateCreationDTO.
     *
     * @param giftCertificateCreationDTO object for validate.
     */
    public static void isGiftCertificateCreationDTOValid(GiftCertificateCreationDTO giftCertificateCreationDTO) {
        String name = giftCertificateCreationDTO.getName();
        String description = giftCertificateCreationDTO.getDescription();
        BigDecimal price = giftCertificateCreationDTO.getPrice();
        int duration = giftCertificateCreationDTO.getDuration();
        List<Tag> tagList = giftCertificateCreationDTO.getTags();
        StringBuilder errorMessage = new StringBuilder();
        if (!isNameValid(name)) {
            errorMessage.append("Gift certificate name is not valid");
        }
        if (!isDescriptionValid(description)) {
            errorMessage.append("Gift certificate description is not valid");
        }
        if (!isPriceValid(price)) {
            errorMessage.append("Gift certificate price is not valid");
        }
        if (!isDurationValid(duration)) {
            errorMessage.append("Gift certificate price is not valid");
        }
        if (tagList != null) {
            for (Tag tag : tagList) {
                String tagName = tag.getName();
                if (!TagValidator.isNameValid(tagName)) {
                    errorMessage.append("Tag name ").append(tagName).append(" is not valid. ");
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
        return description != null && !description.isEmpty() && description.length() < 1000;
    }

    private static boolean isPriceValid(BigDecimal price) {
        return (price != null && price.compareTo(BigDecimal.ZERO) > 0);
    }

    private static boolean isDurationValid(int duration) {
        return duration > 0;
    }
}
