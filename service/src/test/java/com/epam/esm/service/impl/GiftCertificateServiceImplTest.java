package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.ParamContainer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.service.GiftCertificateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GiftCertificateServiceImplTest {
    private static final GiftCertificate giftCertificate = new GiftCertificate(1, "Big", "Yellow",
            new BigDecimal(151), 67, LocalDateTime.of(2013, 9, 13, 12, 12, 12, 123),
            LocalDateTime.of(2013, 9, 13, 12, 12, 12, 123), new ArrayList<>());
    private GiftCertificateService service;
    @Mock
    GiftCertificateDao giftCertificateDao;
    @Mock
    TagDao tagDao;
    @Mock
    GiftCertificatesTagDao giftCertificatesTagDao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void update() {
        long id = 1;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.empty());
    }

    @Test
    void findById() {
        int id = 1;
        GiftCertificate expected = giftCertificate;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(expected));
    }

    @Test
    void delete() {
        int id = 1;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(giftCertificate));
        assertDoesNotThrow(() -> service.delete(id));
    }

    @Test
    void findGiftCertificateByIdWithTagsAndParams() {
        List<GiftCertificate> expected = new ArrayList<>();
        ParamContainer paramContainer = new ParamContainer();
        expected.add(giftCertificate);
        Mockito.when(giftCertificateDao.executeSqlSelect(Mockito.any())).thenReturn(expected);
    }
}