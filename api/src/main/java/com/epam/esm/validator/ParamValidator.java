package com.epam.esm.validator;

import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;

import java.util.regex.Pattern;

public class ParamValidator {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    private ParamValidator() {
    }

    public static void isParamValid(String tagName, String partGiftCertificateName, String partGiftCertificateDescription,
                                    String sortByName, String sortByCreateDate, String sortByLastUpdateDate) {
        StringBuilder errorMessage = new StringBuilder();
        if (!isOrderByValid(sortByCreateDate)) {
            errorMessage.append("Invalid type of sort=").append(sortByCreateDate).append(".");
        }
        if (!isOrderByValid(sortByLastUpdateDate)) {
            errorMessage.append("Invalid type of sort=").append(sortByCreateDate).append(".");
        }
        if (!isOrderByValid(sortByName)) {
            errorMessage.append("Invalid type of sort=").append(sortByCreateDate).append(".");
        }
        if (!TagValidator.isNameValid(tagName)) {
            errorMessage.append("Invalid tag name=").append(tagName).append(".");
        }
        if (!isPartGiftCertificateDescriptionValid(partGiftCertificateDescription)) {
            errorMessage.append("Invalid part of gift certificate description=").append(partGiftCertificateDescription).append(".");
        }
        if (!isPartGiftCertificationNameValid(partGiftCertificateName)) {
            errorMessage.append("Invalid part of gift certificate name=").append(partGiftCertificateName).append(".");
        }
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    private static boolean isOrderByValid(String sortParam) {
        return sortParam.equalsIgnoreCase(ASC) || sortParam.equalsIgnoreCase(DESC);
    }

    private static boolean isPartGiftCertificateDescriptionValid(String partGiftCertificateName) {
        return partGiftCertificateName != null && !partGiftCertificateName.isEmpty() && partGiftCertificateName.length() < 1000;
    }

    private static boolean isPartGiftCertificationNameValid(String partGiftCertificateName) {
        return NAME_REGEX.matcher(partGiftCertificateName).matches();
    }
}
