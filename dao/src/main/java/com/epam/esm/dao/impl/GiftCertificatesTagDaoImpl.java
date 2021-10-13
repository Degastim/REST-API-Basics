package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.constant.sql.GiftCertificatesTagSql;
import com.epam.esm.dao.constant.sql.TagSql;
import com.epam.esm.dao.mapper.GiftCertificatesTagMapper;
import com.epam.esm.entity.GiftCertificatesTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * GiftCertificatesTagDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class GiftCertificatesTagDaoImpl implements GiftCertificatesTagDao {
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificatesTagMapper giftCertificatesTagMapper;

    @Autowired
    public GiftCertificatesTagDaoImpl(JdbcTemplate jdbcTemplate, GiftCertificatesTagMapper giftCertificatesTagMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.giftCertificatesTagMapper = giftCertificatesTagMapper;
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

    @Override
    public Optional<GiftCertificatesTag> findByGiftCertificateIdAndTagId(long giftCertificateId, long tagId) {
        List<GiftCertificatesTag> list=jdbcTemplate.query(GiftCertificatesTagSql.FIND_BY_GIFT_CERTIFICATE_ID_AND_TAG_ID, giftCertificatesTagMapper);
        if (list.size() != 0) {
            return Optional.of(list.get(0));
        } else {
            return Optional.empty();
        }
    }
}
