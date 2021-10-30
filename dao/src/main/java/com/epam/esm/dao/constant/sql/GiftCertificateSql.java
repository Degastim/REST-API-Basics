package com.epam.esm.dao.constant.sql;

/**
 * Class with the gift certificates sql.
 *
 * @author Yauheni Tstiov
 */
public class GiftCertificateSql {
    private GiftCertificateSql() {
    }

    public static final String FIND_BY_NAME_AND_DESCRIPTION_AND_PRICE_AND_DURATION = "SELECT * FROM gift_certificates WHERE gift_certificate_name=? AND description=? AND price=? AND duration=?";
}
