package com.epam.esm.dao.creator;

import com.epam.esm.dao.constant.column.GiftCertificateColumnName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateSqlUpdateCreatorTest {
    private static final GiftCertificateSqlUpdateCreator giftCertificateSqlUpdateCreator = new GiftCertificateSqlUpdateCreator();

    @Test
    @Order(1)
    void addParameter() {
        giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.NAME, "Big");
        String expected = "UPDATE gift_certificates SET gift_certificate_name='Big'";
        String actual = giftCertificateSqlUpdateCreator.getSql().toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    void addWhereEquality() {
        giftCertificateSqlUpdateCreator.addWhereEquality(GiftCertificateColumnName.DURATION, "123");
        String expected = "UPDATE gift_certificates SET gift_certificate_name='Big' WHERE duration='123'";
        String actual = giftCertificateSqlUpdateCreator.getSql().toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void addWhereEqualitySecond() {
        giftCertificateSqlUpdateCreator.addWhereEquality(GiftCertificateColumnName.NAME, "Yellow");
        String expected = "UPDATE gift_certificates SET gift_certificate_name='Big' WHERE duration='123' " +
                "AND gift_certificate_name='Yellow'";
        String actual = giftCertificateSqlUpdateCreator.getSql().toString();
        assertEquals(expected, actual);
    }
}