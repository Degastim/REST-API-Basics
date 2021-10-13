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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateDaoImplTest {
    private static final GiftCertificateDao giftCertificateDao = new GiftCertificateDaoImpl(new JdbcTemplate(new DatabaseConfiguration().embeddedDataSource()), new GiftCertificateMapper());


    @Test
    @Order(2)
    void findById() {
        Optional<GiftCertificate> actual = giftCertificateDao.findById(6);
        GiftCertificate expected = new GiftCertificate(6, "BMV", "Fast and comfortable car", BigDecimal.valueOf(1246), 300, LocalDateTime.of(2022, 10, 25, 11, 11, 11), LocalDateTime.of(2019, 11, 19, 11, 10, 11), new ArrayList<>());
        assertEquals(actual.get(), expected);
    }

    @Test
    @Order(3)
    void findAll() {
        List<GiftCertificate> actualList = giftCertificateDao.findAll();
        long expected = 6;
        assertEquals(actualList.size(), expected);
    }

    @Test
    @Order(4)
    void executeSql() {
        ParamContainer paramContainer=new ParamContainer();
        List<GiftCertificate> actualList = giftCertificateDao.executeSqlSelect(paramContainer);
        long expected = 6;
        assertEquals(actualList.size(), expected);
    }

    @Test
    @Order(5)
    void add() {
        GiftCertificate giftCertificate = new GiftCertificate(1, "Sun", "Yellow",
                new BigDecimal("23"), 67, LocalDateTime.of(2020, 11, 23, 14, 12, 15),
                LocalDateTime.of(2020, 11, 23, 14, 12, 15), new ArrayList<>());
        long expected = 7;
    }

    @Test
    @Order(6)
    void delete() {
        assertDoesNotThrow(() -> giftCertificateDao.delete(6));
    }
}