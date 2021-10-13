package com.epam.esm.dao.impl;

import com.epam.esm.config.DatabaseConfiguration;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.mapper.GiftCertificatesTagMapper;
import com.epam.esm.entity.GiftCertificatesTag;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificatesTagDaoImplTest {
    private static final GiftCertificatesTagDao giftCertificatesTagDao =
            new GiftCertificatesTagDaoImpl(new JdbcTemplate(new DatabaseConfiguration().embeddedDataSource()), new GiftCertificatesTagMapper());

    @Test
    @Order(1)
    void add() {
        long giftCertificateId = 6;
        long tagId = 5;
        assertDoesNotThrow(() -> giftCertificatesTagDao.add(giftCertificateId, tagId));
    }

    @Test
    @Order(2)
    void deleteByGiftCertificateId() {
        long giftCertificateId = 6;
        assertDoesNotThrow(() -> giftCertificatesTagDao.deleteByGiftCertificateId(giftCertificateId));
    }

    @Test
    @Order(3)
    void deleteByTagId() {
        long tagId = 5;
        assertDoesNotThrow(() -> giftCertificatesTagDao.deleteByGiftCertificateId(tagId));
    }

    @Test
    @Order(4)
    void findByGiftCertificateIdAndTagId() {
        long giftCertificateId = 3;
        long tagId = 3;
        GiftCertificatesTag actual = giftCertificatesTagDao.findByGiftCertificateIdAndTagId(giftCertificateId, tagId)
                .get();
        GiftCertificatesTag expected = new GiftCertificatesTag(3, giftCertificateId, tagId);
        assertEquals(expected, actual);
    }
}