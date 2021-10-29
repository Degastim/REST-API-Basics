package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.column.GiftCertificateColumnName;
import com.epam.esm.dao.constant.column.TagColumnName;
import com.epam.esm.dao.constant.sql.GiftCertificateSql;
import com.epam.esm.dao.creator.GiftCertificateSqlSelectCreator;
import com.epam.esm.dao.creator.GiftCertificateSqlUpdateCreator;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dto.ParamContainer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceNotAddedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public GiftCertificate add(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String name = giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer duration = giftCertificate.getDuration();
        LocalDateTime createDate = giftCertificate.getCreateDate();
        LocalDateTime lastUpdateDate = giftCertificate.getLastUpdateDate();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(GiftCertificateSql.ADD_GIFT_CERTIFICATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setInt(4, duration);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(createDate));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(lastUpdateDate));
            return preparedStatement;
        }, keyHolder);
        if (keyHolder.getKey() == null) {
            throw new ResourceNotAddedException("GiftCertificate not Add.No KeyHolder", ExceptionCauseCode.GIFT_CERTIFICATE);
        }
        long giftCertificateId = keyHolder.getKey().longValue();
        return new GiftCertificate(giftCertificateId, name, description, price, duration, createDate, lastUpdateDate);
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
        long id = giftCertificate.getId();
        String giftCertificateName = giftCertificate.getName();
        String giftCertificateDescription = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer duration = giftCertificate.getDuration();
        GiftCertificateSqlUpdateCreator giftCertificateSqlUpdateCreator = new GiftCertificateSqlUpdateCreator();
        if (giftCertificateName != null) {
            giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.NAME, giftCertificateName);
        }
        if (giftCertificateDescription != null) {
            giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.DESCRIPTION,
                    giftCertificateDescription);
        }
        if (price != null) {
            giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.PRICE, price.toString());
        }
        if (duration != null) {
            giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.DURATION, String.valueOf(duration));
        }
        giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.LAST_UPDATE_DATE,
                LocalDateTime.now().toString());
        giftCertificateSqlUpdateCreator.addWhereEquality(GiftCertificateColumnName.ID, Long.toString(id));
        StringBuilder sql = giftCertificateSqlUpdateCreator.getSql();
        jdbcTemplate.update(sql.toString());
    }

    @Override
    public List<GiftCertificate> executeSqlSelect(ParamContainer paramContainer) {
        String tagName = paramContainer.getTagName();
        String partGiftCertificateName = paramContainer.getPartGiftCertificateName();
        String partGiftCertificateDescription = paramContainer.getPartGiftCertificateDescription();
        String sortByName = paramContainer.getSortByName();
        String sortByCreateDate = paramContainer.getSortByCreateDate();
        String sortByLastUpdateDate = paramContainer.getSortByLastUpdateDate();
        GiftCertificateSqlSelectCreator giftCertificateSqlSelectCreator = new GiftCertificateSqlSelectCreator();
        if (tagName != null) {
            giftCertificateSqlSelectCreator.addWhereEquality(TagColumnName.TAG_NAME, tagName);
        }
        if (partGiftCertificateName != null) {
            giftCertificateSqlSelectCreator.addWhereLike(GiftCertificateColumnName.NAME, partGiftCertificateName);
        }
        if (partGiftCertificateDescription != null) {
            giftCertificateSqlSelectCreator.addWhereLike(GiftCertificateColumnName.DESCRIPTION,
                    partGiftCertificateDescription);
        }
        if (sortByName != null) {
            giftCertificateSqlSelectCreator.addOrderBy(GiftCertificateColumnName.NAME, sortByName);
        }
        if (sortByCreateDate != null) {
            giftCertificateSqlSelectCreator.addOrderBy(GiftCertificateColumnName.NAME, sortByCreateDate);
        }
        if (sortByLastUpdateDate != null) {
            giftCertificateSqlSelectCreator.addOrderBy(GiftCertificateColumnName.LAST_UPDATE_DATE, sortByLastUpdateDate);
        }
        StringBuilder sql = giftCertificateSqlSelectCreator.getSql();
        return jdbcTemplate.query(sql.toString(), mapper);
    }

    @Override
    public Optional<GiftCertificate> findByNameAndDescriptionAndPriceAndDuration(String name, String description, BigDecimal price, Integer duration) {
        List<GiftCertificate> giftCertificateList = jdbcTemplate.query(GiftCertificateSql.FIND_BY_NAME_AND_DESCRIPTION_AND_PRICE_AND_DURATION, mapper, name, description, price, duration);
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
