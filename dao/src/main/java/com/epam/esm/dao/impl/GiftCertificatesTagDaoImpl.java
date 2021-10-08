package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.constant.sql.GiftCertificatesTagSql;
import com.epam.esm.dao.constant.sql.TagSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * GiftCertificatesTagDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class GiftCertificatesTagDaoImpl implements GiftCertificatesTagDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificatesTagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(long giftCertificateId, long tagId) {
        jdbcTemplate.update(GiftCertificatesTagSql.ADD_GIFT_CERTIFICATES_TAG, giftCertificateId, tagId);
    }

    @Override
    public void deleteByGiftCertificateId(long id) {
        jdbcTemplate.update(GiftCertificatesTagSql.DELETE_BY_GIFT_CERTIFICATE_ID, id);
    }

    @Override
    public void deleteByTagId(long id) {
        jdbcTemplate.update(TagSql.DELETE_BY_TAG_ID, id);
    }
}
