package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.column.GiftCertificateColumnName;
import com.epam.esm.dao.constant.column.TagColumnName;
import com.epam.esm.dao.creator.GiftCertificateSqlSelectCreator;
import com.epam.esm.dao.creator.GiftCertificateSqlUpdateCreator;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceAlreadyExistException;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * GiftCertificateService implementation.
 *
 * @author Yauheni Tsitov
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;
    private final GiftCertificatesTagDao giftCertificatesTagDao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao,
                                      GiftCertificatesTagDao giftCertificatesTagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
        this.giftCertificatesTagDao = giftCertificatesTagDao;
    }

    @Override
    @Transactional
    public void add(GiftCertificate giftCertificate) {
        List<Tag> tagList = giftCertificate.getTags();
        String name = giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer duration = giftCertificate.getDuration();
        if (giftCertificateDao.findByNameAndDescriptionAndPriceAndDuration(name, description, price, duration).isPresent()) {
            throw new ResourceAlreadyExistException("Gift certification with this name,description,price and duration already exist",
                    ExceptionCauseCode.GIFT_CERTIFICATE);
        }
        StringBuilder errorMessage = new StringBuilder();
        checkTags(tagList, errorMessage);
        if (errorMessage.length() != 0) {
            throw new ResourceNotFoundedException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        giftCertificate.setCreateDate(localDateTime);
        giftCertificate.setLastUpdateDate(localDateTime);
        long giftCertificateId = giftCertificateDao.add(giftCertificate);
        giftCertificate.setId(giftCertificateId);
        giftCertificate.setTags(new ArrayList<>());
        if (tagList != null) {
            for (Tag tag : tagList) {
                connectGiftCertificateAndTag(giftCertificate, tag);
            }
        }
    }

    @Override
    @Transactional
    public void update(long id, GiftCertificate newGiftCertificate) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            GiftCertificateSqlUpdateCreator giftCertificateSqlUpdateCreator = new GiftCertificateSqlUpdateCreator();
            GiftCertificate oldGiftCertificate = giftCertificateOptional.get();
            String newGiftCertificateName = newGiftCertificate.getName();
            String newGiftCertificateDescription = newGiftCertificate.getDescription();
            BigDecimal newPrice = newGiftCertificate.getPrice();
            Integer newDuration = newGiftCertificate.getDuration();
            List<Tag> newTags = newGiftCertificate.getTags();
            StringBuilder errorMessage = new StringBuilder();
            if (newGiftCertificateName != null) {
                giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.NAME, newGiftCertificateName);
            }
            if (newGiftCertificateDescription != null) {
                giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.DESCRIPTION,
                        newGiftCertificateDescription);
            }
            if (newPrice != null) {
                giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.PRICE, newPrice.toString());
            }
            if (newDuration != null) {
                giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.DURATION, String.valueOf(newDuration));
            }
            checkTags(newTags, errorMessage);
            if (errorMessage.length() == 0) {
                giftCertificateSqlUpdateCreator.addParameter(GiftCertificateColumnName.LAST_UPDATE_DATE,
                        LocalDateTime.now().toString());
                giftCertificateSqlUpdateCreator.addWhereEquality(GiftCertificateColumnName.ID, Long.toString(id));
                StringBuilder sql = giftCertificateSqlUpdateCreator.getSql();
                giftCertificateDao.executeSqlUpdate(sql);
                if (newTags != null) {
                    giftCertificatesTagDao.deleteByGiftCertificateId(id);
                    oldGiftCertificate.setTags(new ArrayList<>());
                    newTags = new ArrayList<>(newGiftCertificate.getTags());
                    for (Tag newTag : newTags) {
                        if (newTag.getId() != 0) {
                            tagDao.update(newTag);
                        } else {
                            oldGiftCertificate.setId(id);
                        }
                        connectGiftCertificateAndTag(oldGiftCertificate, newTag);
                    }
                }
            } else {
                throw new ResourceNotFoundedException(errorMessage.toString(), ExceptionCauseCode.GIFT_CERTIFICATE);
            }
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id,
                    ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    @Override
    public GiftCertificate findById(long id) {
        Optional<GiftCertificate> giftCertificate = giftCertificateDao.findById(id);
        if (giftCertificate.isPresent()) {
            return giftCertificate.get();
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id,
                    ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            GiftCertificate giftCertificate = giftCertificateOptional.get();
            if (giftCertificate.getTags().size() != 0) {
                giftCertificatesTagDao.deleteByGiftCertificateId(id);
            }
            giftCertificateDao.delete(id);
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id,
                    ExceptionCauseCode.GIFT_CERTIFICATE);
        }
    }

    @Override
    public List<GiftCertificate> findGiftCertificateByIdWithTagsAndParams(String tagName, String partGiftCertificateName,
                                                                          String partGiftCertificateDescription,
                                                                          String sortByName, String sortByCreateDate,
                                                                          String sortByLastUpdateDate) {
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
        List<GiftCertificate> giftCertificateList = giftCertificateDao.executeSqlSelect(sql);
        if (giftCertificateList.size() != 0) {
            return giftCertificateList;
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (All)", ExceptionCauseCode.GIFT_CERTIFICATE);
        }

    }

    private void connectGiftCertificateAndTag(GiftCertificate giftCertificate, Tag tag) {
        long id = giftCertificate.getId();
        long daoTagId;
        Optional<Tag> daoTagOptional = tagDao.findByName(tag.getName());
        if (daoTagOptional.isPresent()) {
            Tag daoTag = daoTagOptional.get();
            List<Tag> oldTagList = giftCertificate.getTags();
            boolean matchResult = oldTagList.stream().noneMatch(t -> t.getName().equals(daoTag.getName()));
            if (matchResult) {
                daoTagId = daoTag.getId();
                giftCertificatesTagDao.add(id, daoTagId);
            }
        } else {
            daoTagId = tagDao.add(tag);
            giftCertificatesTagDao.add(id, daoTagId);
        }
    }

    private void checkTags(List<Tag> tagList, StringBuilder errorMessage) {
        if (tagList != null) {
            for (Tag tag : tagList) {
                long tagId = tag.getId();
                if (tagId != 0 && !tagDao.findById(tagId).isPresent()) {
                    errorMessage.append("Tag with this id=").append(tagId).append("is not founded. ");
                }
            }
        }
    }
}
