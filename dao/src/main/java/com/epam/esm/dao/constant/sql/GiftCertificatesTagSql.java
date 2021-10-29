package com.epam.esm.dao.constant.sql;

/**
 * Class with the gift certificates and tag sql.
 *
 * @author Yauheni Tstiov
 */
public class GiftCertificatesTagSql {
    private GiftCertificatesTagSql() {
    }

    public static final String ADD_GIFT_CERTIFICATES_TAG = "INSERT INTO gift_certificates_tags(gift_certificate_id, tag_id) " +
            "VALUES(?,?)";
    public static final String DELETE_BY_GIFT_CERTIFICATE_ID = "DELETE FROM gift_certificates_tags WHERE gift_certificate_id=?";

    public static final String FIND_BY_GIFT_CERTIFICATE_ID_AND_TAG_ID="SELECT gift_certificate_tag_id, gift_certificate_id, " +
            "tag_id FROM gift_certificates_tags WHERE gift_certificate_id=? AND tag_id=?";
}
