package com.epam.esm.validator;

import com.epam.esm.dto.ParamContainer;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ParamValidator {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private static final Pattern TAG_NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    public void isParamValid(ParamContainer paramContainer) {
        String tagName = paramContainer.getTagName();
        String partGiftCertificateName = paramContainer.getPartGiftCertificateName();
        String partGiftCertificateDescription = paramContainer.getPartGiftCertificateDescription();
        String sortByName = paramContainer.getSortByName();
        String sortByCreateDate = paramContainer.getSortByCreateDate();
        String sortByLastUpdateDate = paramContainer.getSortByLastUpdateDate();
        StringBuilder errorMessage = new StringBuilder();
        if (sortByCreateDate != null && !isOrderByValid(sortByCreateDate)) {
            errorMessage.append("Invalid type of sort=").append(sortByCreateDate).append(".");
        }
        if (sortByLastUpdateDate != null && !isOrderByValid(sortByLastUpdateDate)) {
            errorMessage.append("Invalid type of sort=").append(sortByCreateDate).append(".");
        }
        if (sortByName != null && !isOrderByValid(sortByName)) {
            errorMessage.append("Invalid type of sort=").append(sortByCreateDate).append(".");
        }
        if (tagName != null && !isTagNameValid(tagName)) {
            errorMessage.append("Invalid tag name=").append(tagName).append(".");
        }
        if (partGiftCertificateDescription != null && !isPartGiftCertificateDescriptionValid(partGiftCertificateDescription)) {
            errorMessage.append("Invalid part of gift certificate description=").append(partGiftCertificateDescription).append(".");
        }
        if (partGiftCertificateName != null && !isPartGiftCertificationNameValid(partGiftCertificateName)) {
            errorMessage.append("Invalid part of gift certificate name=").append(partGiftCertificateName).append(".");
        }
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    private boolean isOrderByValid(String sortParam) {
        return sortParam.equalsIgnoreCase(ASC) || sortParam.equalsIgnoreCase(DESC);
    }

    private boolean isPartGiftCertificateDescriptionValid(String partGiftCertificateName) {
        return partGiftCertificateName != null && !partGiftCertificateName.isEmpty() && partGiftCertificateName.length() < 1000;
    }

    private boolean isPartGiftCertificationNameValid(String partGiftCertificateName) {
        return NAME_REGEX.matcher(partGiftCertificateName).matches();
    }

    private boolean isTagNameValid(String name) {
        return TAG_NAME_REGEX.matcher(name).matches();
    }
}

