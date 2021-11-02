package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.sql.GiftCertificateSql;
import com.epam.esm.dao.creator.GiftCertificateSqlSelectCreator;
import com.epam.esm.dto.param.ParamColumnName;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.dto.param.ParamType;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * GiftCertificateDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public GiftCertificateDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(GiftCertificate giftCertificate) {
        Session session = sessionFactory.getCurrentSession();
        Set<Tag> tags = new HashSet<>(giftCertificate.getTags());
        giftCertificate.setTags(null);
        session.persist(giftCertificate);
        giftCertificate.setTags(tags);
        session.flush();
    }

    @Override
    public void delete(GiftCertificate giftCertificate) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(giftCertificate);
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        Session session = sessionFactory.openSession();
        GiftCertificate giftCertificate = session.find(GiftCertificate.class, id);
        session.clear();
        return Optional.ofNullable(giftCertificate);
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        Session session = sessionFactory.getCurrentSession();
        long id = giftCertificate.getId();
        String giftCertificateName = giftCertificate.getGiftCertificateName();
        String giftCertificateDescription = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer duration = giftCertificate.getDuration();
        Set<Tag> tags = giftCertificate.getTags();
        GiftCertificate daoGiftCertificate = session.find(GiftCertificate.class, id);
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
    public List<GiftCertificate> executeSqlSelect(ParamContainer paramContainer) {
        Session session = sessionFactory.openSession();
        GiftCertificateSqlSelectCreator creator = new GiftCertificateSqlSelectCreator();
        List<String> columnList = paramContainer.getColumn();
        List<String> typeList = paramContainer.getType();
        List<String> paramList = paramContainer.getParam();
        List<GiftCertificate> giftCertificateListWithTag = null;
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
        String sql = creator.getSql().toString();
        List<GiftCertificate> giftCertificateList = session.createSQLQuery(sql).addEntity(GiftCertificate.class).list();
        if (giftCertificateListWithTag != null) {
            return giftCertificateList.stream().filter(o -> giftCertificateListWithTag.contains(o))
                    .collect(Collectors.toList());
        } else {
            return giftCertificateList;
        }
    }

    @Override
    public Optional<GiftCertificate> findByNameAndDescriptionAndPriceAndDuration(String name, String description,
                                                                                 BigDecimal price, Integer duration) {
        List<GiftCertificate> giftCertificateList = sessionFactory.openSession()
                .createSQLQuery(GiftCertificateSql.FIND_BY_NAME_AND_DESCRIPTION_AND_PRICE_AND_DURATION)
                .setParameter(1, name).setParameter(2, description)
                .setParameter(3, price).setParameter(4, duration).addEntity(GiftCertificate.class).list();
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
