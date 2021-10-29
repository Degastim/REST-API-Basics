package com.epam.esm.dao.impl;

import com.epam.esm.config.DatabaseConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dto.ParamContainer;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateDaoImplTest {
    private static final GiftCertificateDao giftCertificateDao = new GiftCertificateDaoImpl(new JdbcTemplate(new DatabaseConfiguration().embeddedDataSource()), new GiftCertificateMapper());

    @Test
    @Order(1)
    void add() {
        GiftCertificate giftCertificate = new GiftCertificate("ABC", "Dig", BigDecimal.TEN, 12,
                LocalDateTime.of(2020, 3, 14, 23, 42, 11),
                LocalDateTime.of(2020, 3, 14, 23, 42, 11));
        GiftCertificate actual = giftCertificateDao.add(giftCertificate);
        giftCertificate.setId(7);
        assertEquals(giftCertificate, actual);
    }

    @Test
    @Order(2)
    void findById() {
        long id = 6;
        GiftCertificate actual = giftCertificateDao.findById(id).get();
        GiftCertificate expected = new GiftCertificate(id, "BMV", "Fast and comfortable car", BigDecimal.valueOf(1246),
                300, LocalDateTime.of(2022, 10, 25, 11, 11, 11),
                LocalDateTime.of(2019, 11, 19, 11, 10, 11), new ArrayList<>());
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void executeSqlSelect() {
        ParamContainer paramContainer = new ParamContainer();
        paramContainer.setPartGiftCertificateName("B");
        System.out.println(giftCertificateDao.executeSqlSelect(new ParamContainer()));
        GiftCertificate actual = giftCertificateDao.executeSqlSelect(paramContainer).get(0);
        GiftCertificate expected = new GiftCertificate(6, "BMV", "Fast and comfortable car", BigDecimal.valueOf(1246),
                300, LocalDateTime.of(2022, 10, 25, 11, 11, 11),
                LocalDateTime.of(2019, 11, 19, 11, 10, 11), new ArrayList<>());
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    void findByNameAndDescriptionAndPriceAndDuration() {
        GiftCertificate actual = giftCertificateDao.findByNameAndDescriptionAndPriceAndDuration("BMV", "Fast and comfortable car", BigDecimal.valueOf(1246), 300).get();
        GiftCertificate expected = new GiftCertificate(6, "BMV", "Fast and comfortable car", BigDecimal.valueOf(1246),
                300, LocalDateTime.of(2022, 10, 25, 11, 11, 11),
                LocalDateTime.of(2019, 11, 19, 11, 10, 11), new ArrayList<>());
        assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    void update() {
        long id = 6;
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(id);
        giftCertificate.setName("ABS");
        assertDoesNotThrow(() -> giftCertificateDao.update(giftCertificate));
    }
}