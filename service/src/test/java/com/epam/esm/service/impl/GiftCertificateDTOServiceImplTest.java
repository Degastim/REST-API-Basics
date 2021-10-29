package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.certificate.GiftCertificateDTOMapper;
import com.epam.esm.mapper.certificate.GiftCertificateResponseDTOMapper;
import com.epam.esm.service.GiftCertificateDTOService;
import com.epam.esm.validator.GiftCertificateDTOValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateDTOServiceImplTest {
    private GiftCertificateDTOService service;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private TagDao tagDao;
    @Mock
    private GiftCertificatesTagDao giftCertificatesTagDao;
    @Mock
    private GiftCertificateDTOValidator giftCertificateDTOValidator;
    @Mock
    private GiftCertificateDTOMapper giftCertificateDTOMapper;
    @Mock
    private GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new GiftCertificateDTOServiceImpl(giftCertificateDao, tagDao, giftCertificatesTagDao,
                giftCertificateDTOValidator, giftCertificateDTOMapper, giftCertificateResponseDTOMapper);
    }

    @Test
    void update() {
        long id = 1;
        String name = "BAAY";
        GiftCertificateDTO giftCertificateCreationDTO = new GiftCertificateDTO();
        giftCertificateCreationDTO.setName(name);
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName(name);
        GiftCertificateResponseDTO giftCertificateResponseDTO = new GiftCertificateResponseDTO(id, name, "Big price",
                BigDecimal.valueOf(151), 67, null, null, new ArrayList<>());
        Mockito.when(giftCertificateDTOMapper.toGiftCertificate(Mockito.any())).thenReturn(giftCertificate);
        Mockito.when(giftCertificateResponseDTOMapper.toDto(Mockito.any())).thenReturn(giftCertificateResponseDTO);
        GiftCertificate certificate = new GiftCertificate(id, name, "Big price",
                BigDecimal.valueOf(151), 67, null, null, new ArrayList<>());
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(certificate));
        GiftCertificateResponseDTO actual = service.update(id, giftCertificateCreationDTO);
        assertEquals(actual, giftCertificateResponseDTO);
    }
}