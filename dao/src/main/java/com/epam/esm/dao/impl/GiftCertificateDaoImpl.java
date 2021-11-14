package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.sql.GiftCertificateSql;
import com.epam.esm.dao.creator.GiftCertificateSqlSelectCreator;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.param.ParamColumnName;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.dto.param.ParamType;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * GiftCertificateDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(GiftCertificate giftCertificate) {
        entityManager.persist(giftCertificate);
    }

    @Override
    public void delete(GiftCertificate giftCertificate) {
        entityManager.remove(giftCertificate);
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, id);
        return Optional.ofNullable(giftCertificate);
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        long id = giftCertificate.getId();
        String giftCertificateName = giftCertificate.getGiftCertificateName();
        String giftCertificateDescription = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer duration = giftCertificate.getDuration();
        Set<Tag> tags = giftCertificate.getTags();
        GiftCertificate daoGiftCertificate = entityManager.find(GiftCertificate.class, id);
        if (giftCertificateName != null) {
            daoGiftCertificate.setGiftCertificateName(giftCertificateName);
        }
        if (giftCertificateDescription != null) {
            daoGiftCertificate.setDescription(giftCertificateDescription);
        }
        if (price != null) {
            daoGiftCertificate.setPrice(price);
        }
        if (duration != null) {
            daoGiftCertificate.setDuration(duration);
        }
        if (tags != null) {
            daoGiftCertificate.setTags(tags);
        }
        return daoGiftCertificate;
    }

    @Override
    public List<GiftCertificate> executeSqlSelect(ParamContainer paramContainer, PaginationContainer paginationContainer) {
        GiftCertificateSqlSelectCreator creator = new GiftCertificateSqlSelectCreator();
        List<String> columnList = paramContainer.getColumn();
        List<String> typeList = paramContainer.getType();
        List<String> paramList = paramContainer.getParam();
        if (columnList != null) {
            for (int i = 0; i < columnList.size(); i++) {
                String column = columnList.get(i);
                String type = typeList.get(i);
                String param = paramList.get(i);
                String dbColumn = ParamColumnName.valueOf(column.toUpperCase()).getColumn();
                if (ParamType.EQUALITY == ParamType.valueOf(type.toUpperCase())) {
                    if (ParamColumnName.valueOf(column.toUpperCase()) == ParamColumnName.TAG_NAME) {
                        creator.addWhereEqualityTagName(param);
                    } else {
                        creator.addWhereEquality(dbColumn, param);
                    }
                }
                if (ParamType.LIKE == ParamType.valueOf(type.toUpperCase())) {
                    creator.addWhereLike(dbColumn, param);
                }
            }
            for (int i = 0; i < columnList.size(); i++) {
                String column = columnList.get(i);
                String type = typeList.get(i);
                String param = paramList.get(i);
                if (ParamType.SORT == ParamType.valueOf(type.toUpperCase())) {
                    String dbColumn = ParamColumnName.valueOf(column.toUpperCase()).getColumn();
                    creator.addOrderBy(dbColumn, param);
                }
            }
        }
        int size = paginationContainer.getSize();
        if (size != 0) {
            creator.limit(paginationContainer);
        }
        String sql = creator.getSql().toString();
        return (List<GiftCertificate>) entityManager.createNativeQuery(sql, GiftCertificate.class).getResultList();
    }

    @Override
    public Optional<GiftCertificate> findByNameAndDescriptionAndPriceAndDuration(String name, String description,
                                                                                 BigDecimal price, Integer duration) {
        List<GiftCertificate> giftCertificateList =
                entityManager.createNativeQuery(GiftCertificateSql.FIND_BY_NAME_AND_DESCRIPTION_AND_PRICE_AND_DURATION,
                                GiftCertificate.class)
                        .setParameter(1, name).setParameter(2, description)
                        .setParameter(3, price).setParameter(4, duration).getResultList();
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
