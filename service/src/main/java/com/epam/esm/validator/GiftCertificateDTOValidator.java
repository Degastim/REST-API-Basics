package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceNotFoundedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
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
    private final TagDao tagDao;

    @Autowired
    private GiftCertificateDTOValidator(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
    }

    /**
     * Validate GiftCertificateDTO.
     *
     * @param giftCertificateDTO object for validate.
     */
    public void isGiftCertificateDTOValid(GiftCertificateDTO giftCertificateDTO) {
        StringBuilder errorMessage = new StringBuilder();
        long id = giftCertificateDTO.getId();
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
        giftCertificateDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.GIFT_CERTIFICATE));
        if (tagList != null) {
            for (Tag tag : tagList) {
                long tagId = tag.getId();
                String tagName = tag.getName();
                if (tagId != 0) {
                    if (tagName != null && !tagDao.findByIdAndName(tagId, tagName).isPresent()) {
                        errorMessage.append("Tag with this id=").append(tagId).append("and name=").append(tagName)
                                .append("is not founded. ");
                    }
                    if (tagName == null && !tagDao.findById(tagId).isPresent()) {
                        errorMessage.append("Tag with this id=").append(tagId).append("is not founded. ");
                    }
                }
                if (tagName != null && !isTagNameValid(tagName)) {
                    errorMessage.append("Tag name").append(tagName).append("is not valid");
                }
            }
        }


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
    public boolean isTagNameValid(String name) {
        return TAG_NAME_REGEX.matcher(name).matches();
    }
}
