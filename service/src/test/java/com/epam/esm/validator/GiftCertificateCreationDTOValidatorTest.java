package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateCreationDTOValidatorTest {
    private GiftCertificateCreationDTOValidator giftCertificateCreationDTOValidator;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private TagDao tagDao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        giftCertificateCreationDTOValidator = new GiftCertificateCreationDTOValidator(giftCertificateDao, tagDao);
    }

    @Test
    void isGiftCertificateCreationDTOValid() {
        String name = "BAAY";
        String description = "GHDGHS";
        BigDecimal price = BigDecimal.TEN;
        Integer duration = 12;
        GiftCertificateCreationDTO giftCertificateCreationDTO = new GiftCertificateCreationDTO(name, description, price, duration);
        Mockito.when(giftCertificateDao.findByNameAndDescriptionAndPriceAndDuration(name, description, price, duration)).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> giftCertificateCreationDTOValidator.isGiftCertificateCreationDTOValid(giftCertificateCreationDTO));
    }
}