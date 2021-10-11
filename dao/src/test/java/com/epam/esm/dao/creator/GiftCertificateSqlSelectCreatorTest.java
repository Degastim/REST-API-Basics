package com.epam.esm.dao.creator;

import com.epam.esm.dao.constant.column.GiftCertificateColumnName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class GiftCertificateSqlSelectCreatorTest {
    private final GiftCertificateSqlSelectCreator giftCertificateSqlSelectCreator = new GiftCertificateSqlSelectCreator();

    @Order(1)
    void addWhereLike() {
        giftCertificateSqlSelectCreator.addWhereLike(GiftCertificateColumnName.NAME, "abc");
        StringBuilder actual = giftCertificateSqlSelectCreator.getSql();
        String expected = "SELECT gift_certificates.*,tags.* FROM gift_certificates LEFT JOIN gift_certificates_tags ON gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id LEFT JOIN tags ON gift_certificates_tags.tag_id=tags.tag_id  WHERE gift_certificate_name LIKE '%abc%'";
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void addWhereEquality() {
        giftCertificateSqlSelectCreator.addWhereEquality(GiftCertificateColumnName.DESCRIPTION, "abc");
        StringBuilder actual = giftCertificateSqlSelectCreator.getSql();
        String expected = "SELECT gift_certificates.*,tags.* FROM gift_certificates LEFT JOIN gift_certificates_tags ON gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id LEFT JOIN tags ON gift_certificates_tags.tag_id=tags.tag_id  WHERE description='abc' ";
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void addOrderBy() {
        giftCertificateSqlSelectCreator.addWhereEquality(GiftCertificateColumnName.NAME, "ASC");
        StringBuilder actual = giftCertificateSqlSelectCreator.getSql();
        String expected = "SELECT gift_certificates.*,tags.* FROM gift_certificates LEFT JOIN gift_certificates_tags ON gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id LEFT JOIN tags ON gift_certificates_tags.tag_id=tags.tag_id  WHERE gift_certificate_name='ASC' ";
        Assertions.assertEquals(expected, actual.toString());
    }
}