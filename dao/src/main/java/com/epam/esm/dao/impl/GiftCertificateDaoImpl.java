package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.sql.GiftCertificateSql;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceNotAddedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * GiftCertificateDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper mapper;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, GiftCertificateMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public long add(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(GiftCertificateSql.ADD_GIFT_CERTIFICATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, giftCertificate.getName());
            preparedStatement.setString(2, giftCertificate.getDescription());
            preparedStatement.setBigDecimal(3, giftCertificate.getPrice());
            preparedStatement.setInt(4, giftCertificate.getDuration());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(giftCertificate.getCreateDate()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(giftCertificate.getLastUpdateDate()));
            return preparedStatement;
        }, keyHolder);
        if (keyHolder.getKey() == null) {
            throw new ResourceNotAddedException("GiftCertificate not Add.No KeyHolder");
        }
        return keyHolder.getKey().longValue();
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(GiftCertificateSql.DELETE_GIFT_CERTIFICATE, id);
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        List<GiftCertificate> giftCertificateList = jdbcTemplate.query(GiftCertificateSql.FIND_BY_ID, mapper, id);
        return returnGiftCertificate(giftCertificateList);
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(GiftCertificateSql.UPDATE_BY_ID, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(), giftCertificate.getLastUpdateDate(),
                giftCertificate.getId());
    }

    @Override
    public List<GiftCertificate> executeSql(StringBuilder sql) {
        return jdbcTemplate.query(sql.toString(), mapper);
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(GiftCertificateSql.FIND_BY_ALL, mapper);
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        List<GiftCertificate> giftCertificateList = jdbcTemplate.query(GiftCertificateSql.FIND_BY_NAME, mapper, name);
        return returnGiftCertificate(giftCertificateList);
    }

    private Optional<GiftCertificate> returnGiftCertificate(List<GiftCertificate> giftCertificateList) {
        if (giftCertificateList.size() != 0) {
            return Optional.of(giftCertificateList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
