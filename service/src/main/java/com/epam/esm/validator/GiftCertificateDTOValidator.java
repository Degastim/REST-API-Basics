package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceNotFoundedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Validator for GiftCertificateDTO.
 *
 * @author Yauheni Tsitov
 */
@Component
public class GiftCertificateDTOValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private static final Pattern TAG_NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private final GiftCertificateDao giftCertificateDao;

    @Autowired
    public GiftCertificateDTOValidator(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    /**
     * Validate GiftCertificateDTO.
     *
     * @param giftCertificateDTO object for validate.
     */
    public void isGiftCertificateDTOUpdateValid(GiftCertificateDTO giftCertificateDTO) {
        StringBuilder errorMessage = new StringBuilder();
        long id = giftCertificateDTO.getId();
        String name = giftCertificateDTO.getName();
        String description = giftCertificateDTO.getDescription();
        BigDecimal price = giftCertificateDTO.getPrice();
        Integer duration = giftCertificateDTO.getDuration();
        Set<TagDTO> tagDTOSet = giftCertificateDTO.getTags();
        GiftCertificate giftCertificate = giftCertificateDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.GIFT_CERTIFICATE));
        if (name == null) {
            name = giftCertificate.getGiftCertificateName();
        } else if (!isNameValid(name)) {
            errorMessage.append("Gift certificate name is not valid");
        }
        if (description == null) {
            description = giftCertificate.getDescription();
        } else if (!isDescriptionValid(description)) {
            errorMessage.append("Gift certificate name is not valid");
        }
        if (price == null) {
            price = giftCertificate.getPrice();
        } else if (!isPriceValid(price)) {
            errorMessage.append("Gift certificate price is not valid");
        }
        if (duration == null) {
            duration = giftCertificate.getDuration();
        } else if (!isDurationValid(duration)) {
            errorMessage.append("Gift certificate duration is not valid");
        }
        Optional<GiftCertificate> daoGiftCertificate = giftCertificateDao.
                findByNameAndDescriptionAndPriceAndDuration(name, description, price, duration);
        if (daoGiftCertificate.isPresent() && daoGiftCertificate.get().getId() != id) {
            errorMessage.append("A gift certificate with same name,description,price,duration exist");
        }
        checkTagList(tagDTOSet, errorMessage);
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    public void isGiftCertificateDTOAddValid(GiftCertificateDTO giftCertificateDTO) {
        StringBuilder errorMessage = new StringBuilder();
        String name = giftCertificateDTO.getName();
        String description = giftCertificateDTO.getDescription();
        BigDecimal price = giftCertificateDTO.getPrice();
        Integer duration = giftCertificateDTO.getDuration();
        Set<TagDTO> tagDTOSet = giftCertificateDTO.getTags();
        long giftCertificateId = giftCertificateDTO.getId();
        if (giftCertificateId != 0) {
            errorMessage.append("Can't transfer id gift certificate.");
        }
        if (name == null || !isNameValid(name)) {
            errorMessage.append("Gift certificate name is not valid.");
        }
        if (description == null || !isDescriptionValid(description)) {
            errorMessage.append("Gift certificate name is not valid.");
        }
        if (price == null || !isPriceValid(price)) {
            errorMessage.append("Gift certificate price is not valid.");
        }
        if (duration == null || !isDurationValid(duration)) {
            errorMessage.append("Gift certificate duration is not valid.");
        }
        if (giftCertificateDao.findByNameAndDescriptionAndPriceAndDuration(name, description, price, duration).isPresent()) {
            errorMessage.append("A gift certificate with same name,description,price,duration exist.");
        }
        checkTagList(tagDTOSet, errorMessage);
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    private boolean isNameValid(String name) {
        return NAME_REGEX.matcher(name).matches();
    }

    private boolean isDescriptionValid(String description) {
        return !description.isEmpty() && description.length() < 1000;
    }

    private boolean isPriceValid(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isDurationValid(Integer duration) {
        return duration.compareTo(0) > 0;
    }

    private void checkTagList(Set<TagDTO> tagDTOSet, StringBuilder errorMessage) {
        if (tagDTOSet != null) {
            for (TagDTO tag : tagDTOSet) {
                String tagName = tag.getName();
                long tadId = tag.getId();
                if (tagName == null || !isTagNameValid(tagName)) {
                    errorMessage.append("Tag name ").append(tagName).append(" is not valid");
                }
                if (tadId != 0) {
                    errorMessage.append("Can't transfer id tag");
                }
            }
        }
    }

    private boolean isTagNameValid(String name) {
        return TAG_NAME_REGEX.matcher(name).matches();
    }
}
