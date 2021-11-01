package com.epam.esm.dao.impl;

import com.epam.esm.config.DatabaseConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.math.BigDecimal;
import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateDaoImplTest {

    private GiftCertificateDao giftCertificateDao;

    @BeforeEach
    void init() {
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
        LocalSessionFactoryBean localSessionFactoryBean =
        databaseConfiguration.sessionFactory(new DatabaseConfiguration().embeddedDataSource());
        giftCertificateDao = new GiftCertificateDaoImpl(localSessionFactoryBean.getObject());
    }

    @Test
    void add() {
        GiftCertificate expected = new GiftCertificate("abc", "abc", BigDecimal.TEN, 100
                , new HashSet<>());
        GiftCertificate actual = new GiftCertificate("abc", "abc", BigDecimal.TEN, 100
                , new HashSet<>());
        giftCertificateDao.add(actual);
        actual.setCreateDate(null);
        actual.setOperation(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void executeSqlSelect() {
    }

    @Test
    void findByNameAndDescriptionAndPriceAndDuration() {
    }
}