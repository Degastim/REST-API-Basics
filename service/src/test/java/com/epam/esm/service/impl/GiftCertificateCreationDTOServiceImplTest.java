package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.certificate.GiftCertificateCreationDTOMapper;
import com.epam.esm.mapper.certificate.GiftCertificateResponseDTOMapper;
import com.epam.esm.service.GiftCertificateCreationDTOService;
import com.epam.esm.validator.GiftCertificateCreationDTOValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateCreationDTOServiceImplTest {
    private GiftCertificateCreationDTOService service;
    @Mock
    private GiftCertificateCreationDTOValidator giftCertificateCreationDTOValidator;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private TagDao tagDao;
    @Mock
    private GiftCertificatesTagDao giftCertificatesTagDao;
    @Mock
    GiftCertificateCreationDTOMapper giftCertificateCreationDTOMapper;
    @Mock
    GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new GiftCertificateCreationDTOServiceImpl(giftCertificateDao, tagDao, giftCertificatesTagDao,
                giftCertificateCreationDTOValidator, giftCertificateCreationDTOMapper, giftCertificateResponseDTOMapper);
    }

    @Test
    void add() {
        String name = "BAAY";
        String description = "GHDGHS";
        BigDecimal price = BigDecimal.TEN;
        Integer duration = 12;
        GiftCertificateCreationDTO giftCertificateCreationDTO = new GiftCertificateCreationDTO(name, description, price, duration);
        GiftCertificate giftCertificate = new GiftCertificate(name, description, price, duration, null, null);
        Mockito.when(giftCertificateCreationDTOMapper.toGiftCertificate(Mockito.any())).thenReturn(giftCertificate);
        giftCertificate.setId(7);
        GiftCertificateResponseDTO expected = new GiftCertificateResponseDTO(7, name, description, price, duration, null, null, new ArrayList<>());
        Mockito.when(giftCertificateDao.add(Mockito.any())).thenReturn(giftCertificate);
        Mockito.when(giftCertificateResponseDTOMapper.toDto(Mockito.any())).thenReturn(expected);
        GiftCertificateResponseDTO actual = service.add(giftCertificateCreationDTO);
        actual.setCreateDate(null);
        actual.setLastUpdateDate(null);
        assertEquals(expected, actual);
    }
}