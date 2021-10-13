package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Validator for GiftCertificateCreationDTO.
 *
 * @author Yauheni Tsitov
 */
@Component
public class GiftCertificateCreationDTOValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private static final Pattern TAG_NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;

    @Autowired
    public GiftCertificateCreationDTOValidator(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
    }

    /**
     * Validate GiftCertificateCreationDTO.
     *
     * @param giftCertificateCreationDTO object for validate.
     */
    public void isGiftCertificateCreationDTOValid(GiftCertificateCreationDTO giftCertificateCreationDTO) {
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
                long tagId = tag.getId();
                String tagName = tag.getName();
                if (tagId != 0) {
                    if (tagName != null) {
                        Optional<Tag> daoTagOptional = tagDao.findById(tagId);
                        if (daoTagOptional.isPresent()) {
                            Tag daoTag = daoTagOptional.get();
                            if (!daoTag.getName().equals(tagName)) {
                                errorMessage.append("Tag with this id=").append(tagId).append("and name").append(tagName)
                                        .append("is not founded.");
                            }
                        }
                    }
                    if (tagName == null && !tagDao.findById(tagId).isPresent()) {
                        errorMessage.append("Tag with this id=").append(tagId).append("is not founded.");
                    }
                }
                if (!isTagNameValid(tagName)) {
                    errorMessage.append("Tag name ").append(tagName).append(" is not valid. ");
                }
            }
        }
        if (giftCertificateDao.findByNameAndDescriptionAndPriceAndDuration(name, description, price, duration).isPresent()) {
            errorMessage.append("Gift certification with this name,description,price and duration already exist");
        }
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    private boolean isNameValid(String name) {
        return NAME_REGEX.matcher(name).matches();
    }

    private boolean isDescriptionValid(String description) {
        return description != null && !description.isEmpty() && description.length() < 1000;
    }

    private boolean isPriceValid(BigDecimal price) {
        return (price != null && price.compareTo(BigDecimal.ZERO) > 0);
    }

    private boolean isDurationValid(int duration) {
        return duration > 0;
    }
    public boolean isTagNameValid(String name) {
        return TAG_NAME_REGEX.matcher(name).matches();
    }
}
