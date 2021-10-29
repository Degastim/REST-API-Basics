package com.epam.esm.dao.constant.sql;

/**
 * Class with the gift certificates sql.
 *
 * @author Yauheni Tstiov
 */
public class GiftCertificateSql {
    private GiftCertificateSql() {
    }

    public static final String ADD_GIFT_CERTIFICATE = "INSERT INTO gift_certificates" +
            "(gift_certificate_name, description, price, duration, create_date, last_update_date) VALUES (?,?,?,?,?,?)";
    public static final String DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificates WHERE gift_certificate_id=?";
    public static final String FIND_BY_ID = "SELECT gift_certificates.*, tags.* FROM gift_certificates " +
            "LEFT JOIN gift_certificates_tags ON gift_certificates.gift_certificate_id = gift_certificates_tags.gift_certificate_id " +
            "LEFT JOIN tags ON tags.tag_id = gift_certificates_tags.tag_id WHERE gift_certificates.gift_certificate_id = ? ";
    public static final String FIND_BY_NAME_AND_DESCRIPTION_AND_PRICE_AND_DURATION = "SELECT gift_certificates.*, tags.* " +
            "FROM gift_certificates " +
            "LEFT JOIN gift_certificates_tags ON gift_certificates_tags.gift_certificate_id = gift_certificates.gift_certificate_id " +
            "LEFT JOIN tags ON gift_certificates_tags.tag_id = tags.tag_id " +
            "WHERE gift_certificate_name=? AND description=? AND price=? AND duration=?";
    public static final String FIND_BY_TAG_NAME="SELECT gift_certificates.*,tags.* FROM gift_certificates " +
            "LEFT JOIN gift_certificates_tags ON gift_certificates.gift_certificate_id=gift_certificates_tags.gift_certificate_id " +
            "LEFT JOIN tags ON gift_certificates_tags.tag_id=tags.tag_id WHERE tag_name=?";
}
