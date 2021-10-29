package com.epam.esm.dao.creator;

import com.epam.esm.dao.constant.column.GiftCertificateColumnName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GiftCertificateSqlSelectCreatorTest {
    private static final GiftCertificateSqlSelectCreator giftCertificateSqlSelectCreator = new GiftCertificateSqlSelectCreator();

    @Test
    void addWhereLike() {
        giftCertificateSqlSelectCreator.addWhereLike(GiftCertificateColumnName.DESCRIPTION, "Big");
        String expected = "SELECT gift_certificates.*,tags.* FROM gift_certificates LEFT JOIN gift_certificates_tags ON " +
                "gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id LEFT JOIN tags ON " +
                "gift_certificates_tags.tag_id=tags.tag_id WHERE description LIKE '%Big%'";
        String actual = giftCertificateSqlSelectCreator.getSql().toString();
        assertEquals(expected, actual);
    }

    @Test
    void addWhereEquality() {
        giftCertificateSqlSelectCreator.addWhereEquality(GiftCertificateColumnName.NAME, "Yellow");
        String expected = "SELECT gift_certificates.*,tags.* FROM gift_certificates LEFT JOIN gift_certificates_tags ON " +
                "gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id LEFT JOIN tags ON " +
                "gift_certificates_tags.tag_id=tags.tag_id WHERE description LIKE '%Big%' AND gift_certificate_name='Yellow'";
        String actual = giftCertificateSqlSelectCreator.getSql().toString();
        assertEquals(expected, actual);
    }

    @Test
    void addOrderBy() {
        giftCertificateSqlSelectCreator.addOrderBy(GiftCertificateColumnName.CREATE_DATE, "ASC");
        String expected = "SELECT gift_certificates.*,tags.* FROM gift_certificates LEFT JOIN gift_certificates_tags ON " +
                "gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id LEFT JOIN tags ON " +
                "gift_certificates_tags.tag_id=tags.tag_id WHERE description LIKE '%Big%' AND gift_certificate_name='Yellow' " +
                "ORDER BY create_date ASC";
        String actual = giftCertificateSqlSelectCreator.getSql().toString();
        assertEquals(expected, actual);
    }
}