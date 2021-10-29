package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateServiceImplTest {
    private static final GiftCertificate giftCertificate = new GiftCertificate(1, "Big", "Yellow",
            new BigDecimal(151), 67, LocalDateTime.of(2013, 9, 13, 12, 12, 12, 123),
            LocalDateTime.of(2013, 9, 13, 12, 12, 12, 123), new ArrayList<>());
    private GiftCertificateService service;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private GiftCertificatesTagDao giftCertificatesTagDao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new GiftCertificateServiceImpl(giftCertificateDao, giftCertificatesTagDao);
    }

    @Test
    void findById() {
        int id = 1;
        GiftCertificateResponseDTO expected = new GiftCertificateResponseDTO(1, "Big", "Yellow",
                new BigDecimal(151), 67, LocalDateTime.of(2013, 9, 13, 12, 12, 12, 123),
                LocalDateTime.of(2013, 9, 13, 12, 12, 12, 123), new ArrayList<>());
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(giftCertificate));
        GiftCertificateResponseDTO actual = service.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        int id = 1;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(giftCertificate));
        assertDoesNotThrow(() -> service.delete(id));
    }
}