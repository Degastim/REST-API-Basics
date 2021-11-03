package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.GiftCertificateDTOMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validator.GiftCertificateDTOValidator;
import com.epam.esm.validator.PaginationContainerValidator;
import com.epam.esm.validator.ParamValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateServiceImplTest {
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private GiftCertificateDTOMapper giftCertificateDTOMapper;
    @Mock
    private GiftCertificateDTOValidator giftCertificateDTOValidator;
    @Mock
    private TagDao tagDao;
    @Mock
    private ParamValidator paramValidator;
    @Mock
    private PaginationContainerValidator paginationContainerValidator;
    private GiftCertificateService service;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new GiftCertificateServiceImpl(giftCertificateDao, giftCertificateDTOMapper,
                giftCertificateDTOValidator, tagDao, paramValidator, paginationContainerValidator);
    }

    @Test
    void findById() {
        long id = 3;
        GiftCertificate giftCertificate = new GiftCertificate();
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(giftCertificate));
        GiftCertificateDTO actual = service.findById(3);
        GiftCertificateDTO expected = giftCertificateDTOMapper.toDTO(giftCertificate);
        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        long id = 3;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundedException.class, () -> service.delete(id));
    }

    @Test
    void update() {
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
        giftCertificateDTO.setName("name");
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setGiftCertificateName("name");
        Mockito.when(giftCertificateDTOMapper.toEntity(giftCertificateDTO)).thenReturn(giftCertificate);
        Mockito.when(giftCertificateDao.update(giftCertificate)).thenReturn(giftCertificate);
        Mockito.when(giftCertificateDTOMapper.toDTO(giftCertificate)).thenReturn(giftCertificateDTO);
        GiftCertificateDTO actual = service.update(1, giftCertificateDTO);
        GiftCertificateDTO expected = new GiftCertificateDTO();
        expected.setName("name");
        expected.setId(1);
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO("ABC", "ABC",
                BigDecimal.TEN, 10, new HashSet<>());
        GiftCertificate giftCertificate = new GiftCertificate("ABC", "ABC",
                BigDecimal.TEN, 10, new HashSet<>());
        Mockito.when(giftCertificateDTOMapper.toEntity(giftCertificateDTO)).thenReturn(giftCertificate);
        Mockito.when(giftCertificateDTOMapper.toDTO(giftCertificate)).thenReturn(giftCertificateDTO);
        GiftCertificateDTO actual = service.add(giftCertificateDTO);
        assertEquals(actual, giftCertificateDTO);
    }

    @Test
    void findGiftCertificateByIdWithTagsAndParams() {
        List<GiftCertificateDTO> actual = service.findGiftCertificateByIdWithTagsAndParams(new PaginationContainer(),
                new ParamContainer());
        List<GiftCertificateDTO> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}