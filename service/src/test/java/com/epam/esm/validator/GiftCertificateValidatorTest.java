package com.epam.esm.validator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GiftCertificateValidatorTest {
    private static final String giftCertificateName = "aga";
    private static final String giftCertificateDescription = "Cheese is life";
    private static final BigDecimal giftCertificatePrice = BigDecimal.TEN;
    private static final int giftCertificate = 10;
    private static final LocalDateTime createDate = LocalDateTime.now();

    @Test
    void isNameValid() {
        boolean actual = GiftCertificateValidator.isNameValid(giftCertificateName);
        assertTrue(actual);
    }

    @Test
    void isDescriptionValid() {
        boolean actual = GiftCertificateValidator.isDescriptionValid(giftCertificateDescription);
        assertTrue(actual);
    }

    @Test
    void isPriceValid() {
        boolean actual = GiftCertificateValidator.isPriceValid(giftCertificatePrice);
        assertTrue(actual);
    }

    @Test
    void isDurationValid() {
        boolean actual = GiftCertificateValidator.isDurationValid(giftCertificate);
        assertTrue(actual);
    }

    @Test
    void isCreateDateValid() {
        boolean actual = GiftCertificateValidator.isCreateDateValid(createDate);
        assertTrue(actual);
    }

    @Test
    void isLastUpdateDateValid() {
        boolean actual = GiftCertificateValidator.isLastUpdateDateValid(createDate);
        assertTrue(actual);
    }
}