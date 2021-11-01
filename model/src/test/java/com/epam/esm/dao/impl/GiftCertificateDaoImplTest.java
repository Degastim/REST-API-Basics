package com.epam.esm.dao.impl;

import com.epam.esm.Application;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = Application.class)
class GiftCertificateDaoImplTest {
    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @Test
    @Transactional
    void add() {
        GiftCertificate actual = new GiftCertificate("23", "sfs", BigDecimal.TEN, 10, new HashSet<>());
        LocalDateTime now = LocalDateTime.now();
        actual.setCreateDate(now);
        giftCertificateDao.add(actual);
        GiftCertificate expected = new GiftCertificate(7, "23", "sfs", BigDecimal.TEN, 10, new HashSet<>());
        expected.setCreateDate(now);
        expected.setOperation("INSERT");
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void delete() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(7);
        assertDoesNotThrow(() -> giftCertificateDao.delete(giftCertificate));
    }

    @Test
    void findById() {
        long id = 10;
        Optional<GiftCertificate> actual = giftCertificateDao.findById(id);
        assertTrue(!actual.isPresent());
    }

    @Test
    @Transactional
    void update() {
        GiftCertificate update = new GiftCertificate();
        update.setId(2);
        update.setGiftCertificateName("name");
        GiftCertificate actual = giftCertificateDao.update(update);
        GiftCertificate expected = new GiftCertificate();
        expected.setId(2);
        expected.setOperation("INSERT");
        expected.setLastUpdateDate(LocalDateTime.of(2020, 5, 5, 23, 42, 12, 112000000));
        expected.setGiftCertificateName("name");
        expected.setDescription("Red price");
        expected.setPrice(new BigDecimal("10.00"));
        expected.setDuration(13);
        expected.setCreateDate(LocalDateTime.of(2020, 3, 14, 23, 42, 11, 164000000));
        actual.setOrderList(null);
        expected.setTags(new HashSet<>());
        assertEquals(expected, actual);
    }

    @Test
    void findByNameAndDescriptionAndPriceAndDuration() {
        Optional<GiftCertificate> giftCertificate = giftCertificateDao.findByNameAndDescriptionAndPriceAndDuration("Dig", "Big price", new BigDecimal("151.00"), 67);
        assertTrue(giftCertificate.isPresent());
    }
}