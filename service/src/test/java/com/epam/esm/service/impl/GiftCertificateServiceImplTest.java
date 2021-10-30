package com.epam.esm.service.impl;

import com.epam.esm.Paginator;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDTO;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateServiceImplTest {
    @Mock
    GiftCertificateDao giftCertificateDao;
    @Mock
    GiftCertificateDTOMapper giftCertificateDTOMapper;
    @Mock
    GiftCertificateDTOValidator giftCertificateDTOValidator;
    @Mock
    TagDao tagDao;
    @Mock
    ParamValidator paramValidator;
    @Mock
    PaginationContainerValidator paginationContainerValidator;
    @Mock
    Paginator<GiftCertificate> paginator;
    private GiftCertificateService service;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new GiftCertificateServiceImpl(giftCertificateDao, giftCertificateDTOMapper, giftCertificateDTOValidator, tagDao, paramValidator, paginationContainerValidator, paginator);
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
        assertThrows(ResourceNotFoundedException.class,()->service.delete(id));
    }

    @Test
    void update() {
    }

    @Test
    void add() {
    }

    @Test
    void findGiftCertificateByIdWithTagsAndParams() {
    }
}