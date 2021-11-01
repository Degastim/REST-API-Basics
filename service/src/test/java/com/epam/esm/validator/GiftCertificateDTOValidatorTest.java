package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.InvalidFieldValueException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateDTOValidatorTest {
    private GiftCertificateDTOValidator giftCertificateDTOValidator;
    @Mock
    private GiftCertificateDao giftCertificateDao;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        giftCertificateDTOValidator = new GiftCertificateDTOValidator(giftCertificateDao);
    }

    @Test
    void isGiftCertificateDTOUpdateValid() {
        long id = 3;
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
        giftCertificateDTO.setId(id);
        giftCertificateDTO.setName("123");
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(new GiftCertificate()));
        assertThrows(InvalidFieldValueException.class,
                () -> giftCertificateDTOValidator.isGiftCertificateDTOUpdateValid(giftCertificateDTO));
    }

    @Test
    void isGiftCertificateDTOAddValid() {
        long id = 3;
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
        giftCertificateDTO.setId(id);
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(new GiftCertificate()));
        assertThrows(InvalidFieldValueException.class,
                () -> giftCertificateDTOValidator.isGiftCertificateDTOAddValid(giftCertificateDTO));

    }
}