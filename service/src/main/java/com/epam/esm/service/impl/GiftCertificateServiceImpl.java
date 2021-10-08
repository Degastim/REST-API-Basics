package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.column.GiftCertificateColumnName;
import com.epam.esm.dao.constant.column.TagColumnName;
import com.epam.esm.dao.creator.SqlCreator;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validator.GiftCertificateValidator;
import com.epam.esm.validator.SqlValidator;
import com.epam.esm.validator.TagValidator;
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
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao, GiftCertificatesTagDao giftCertificatesTagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
        this.giftCertificatesTagDao = giftCertificatesTagDao;
    }

    @Override
    @Transactional
    public void add(GiftCertificate giftCertificate) {
        String giftCertificateName = giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        int duration = giftCertificate.getDuration();
        LocalDateTime createDate = giftCertificate.getCreateDate();
        LocalDateTime lastUpdateDate = giftCertificate.getLastUpdateDate();
        List<Tag> tags = giftCertificate.getTags();
        StringBuilder errorMessage = new StringBuilder();
        if (giftCertificateDao.findAll().stream().anyMatch(daoGiftCertificate -> daoGiftCertificate.getName().equals(giftCertificateName))) {
            errorMessage.append("There is already a gift certificate with the same name.");
        }
        checkGiftCertificationName(giftCertificateName, errorMessage);
        checkGiftCertificationDescription(description, errorMessage);
        checkGiftCertificationPrice(price, errorMessage);
        checkGiftCertificationDuration(duration, errorMessage);
        checkCreateDate(createDate, errorMessage);
        checkLastUpdateDate(lastUpdateDate, errorMessage);
        if (tags != null) {
            tags.forEach(tag -> checkTagName(tag.getName(), errorMessage));
        }
        if (errorMessage.length() == 0) {
            LocalDateTime localDateTime = LocalDateTime.now();
            giftCertificate.setCreateDate(localDateTime);
            giftCertificate.setLastUpdateDate(localDateTime);
            long giftCertificateId = giftCertificateDao.add(giftCertificate);
            if (tags != null) {
                for (Tag tag : tags) {
                    giftCertificate.setId(giftCertificateId);
                    giftCertificate.setTags(new ArrayList<>());
                    connectGiftCertificateAndTag(giftCertificate, tag);
                }
            }
        } else {
            throw new InvalidFieldValueException(errorMessage.toString());
        }
    }

    @Override
    @Transactional
    public void update(long id, GiftCertificate newGiftCertificate) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            GiftCertificate oldGiftCertificate = giftCertificateOptional.get();
            String newGiftCertificateName = newGiftCertificate.getName();
            String newGiftCertificateDescription = newGiftCertificate.getDescription();
            BigDecimal newPrice = newGiftCertificate.getPrice();
            int newDuration = newGiftCertificate.getDuration();
            List<Tag> newTags = newGiftCertificate.getTags();
            StringBuilder errorMessage = new StringBuilder();
            if (checkGiftCertificationName(newGiftCertificateName, errorMessage)) {
                if (!giftCertificateDao.findByName(newGiftCertificateName).isPresent()) {
                    oldGiftCertificate.setName(newGiftCertificateName);
                } else {
                    errorMessage.append("Gift certificate with this name has already.");
                }
            }
            if (checkGiftCertificationDescription(newGiftCertificateDescription, errorMessage)) {
                oldGiftCertificate.setDescription(newGiftCertificateDescription);
            }
            if (checkGiftCertificationPrice(newPrice, errorMessage)) {
                oldGiftCertificate.setPrice(newPrice);
            }
            if (checkGiftCertificationDuration(newDuration, errorMessage)) {
                oldGiftCertificate.setDuration(newDuration);
            }
            if (newTags != null) {
                for (Tag newTag : newTags) {
                    long newTagId = newTag.getId();
                    checkTagName(newTag.getName(), errorMessage);
                    if (newTagId != 0) {
                        Optional<Tag> daoTagOptional = tagDao.findById(newTagId);
                        if (!daoTagOptional.isPresent()) {
                            errorMessage.append("Invalid tag id.Not founded.");
                        }
                    }
                }
            }
            if (errorMessage.length() == 0) {
                oldGiftCertificate.setLastUpdateDate(LocalDateTime.now());
                giftCertificateDao.update(oldGiftCertificate);
                if (newTags != null) {
                    for (Tag newTag : newTags) {
                        if (newTag.getId() != 0) {
                            tagDao.update(newTag);
                        } else {
                            oldGiftCertificate.setId(id);
                            connectGiftCertificateAndTag(oldGiftCertificate, newTag);
                        }
                    }
                }
            } else {
                throw new InvalidFieldValueException(errorMessage.toString());
            }
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id);
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

    @Override
    public GiftCertificate findById(long id) {
        Optional<GiftCertificate> giftCertificate = giftCertificateDao.findById(id);
        if (giftCertificate.isPresent()) {
            return giftCertificate.get();
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id);
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
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id);
        }
    }

    @Override
    public List<GiftCertificate> findGiftCertificateByIdWithTagsAndParams(String tagName, String partGiftCertificateName, String partGiftCertificateDescription, String sortByName, String sortByCreateDate, String sortByLastUpdateDate) {
        SqlCreator sqlCreator = new SqlCreator();
        StringBuilder errorMessage = new StringBuilder();
        if (tagName != null) {
            if (checkTagName(tagName, errorMessage)) {
                sqlCreator.addWhereEquality(TagColumnName.TAG_NAME, tagName);
            }
        }
        if (partGiftCertificateName != null) {
            if (checkGiftCertificationName(partGiftCertificateName, errorMessage)) {
                sqlCreator.addWhereLike(GiftCertificateColumnName.NAME, partGiftCertificateName);
            }
        }
        if (partGiftCertificateDescription != null) {
            if (checkGiftCertificationDescription(partGiftCertificateDescription, errorMessage)) {
                sqlCreator.addWhereLike(GiftCertificateColumnName.DESCRIPTION, partGiftCertificateDescription);
            }
        }
        if (sortByName != null) {
            if (checkGiftCertificationName(sortByName, errorMessage)) {
                sqlCreator.addOrderBy(GiftCertificateColumnName.NAME, sortByName);
            }
        }
        if (sortByCreateDate != null) {
            if (checkSqlOrderBy(sortByCreateDate, errorMessage)) {
                sqlCreator.addOrderBy(GiftCertificateColumnName.NAME, sortByCreateDate);
            }
        }
        if (sortByLastUpdateDate != null) {
            if (checkSqlOrderBy(sortByLastUpdateDate, errorMessage)) {
                sqlCreator.addOrderBy(GiftCertificateColumnName.LAST_UPDATE_DATE, sortByLastUpdateDate);
            }
        }
        if (errorMessage.length() == 0) {
            StringBuilder sql = sqlCreator.getSql();
            List<GiftCertificate> giftCertificateList = giftCertificateDao.executeSql(sql);
            if (giftCertificateList.size() != 0) {
                return giftCertificateList;
            } else {
                throw new ResourceNotFoundedException("Requested resource not found (All)");
            }
        } else {
            throw new InvalidFieldValueException(errorMessage.toString());
        }
    }

    private boolean checkGiftCertificationName(String name, StringBuilder errorMessage) {
        boolean result = GiftCertificateValidator.isNameValid(name);
        if (!result) {
            errorMessage.append("Invalid gift certificate name=").append(name).append(".");
        }
        return result;
    }

    private boolean checkGiftCertificationDescription(String description, StringBuilder errorMessage) {
        boolean result = GiftCertificateValidator.isDescriptionValid(description);
        if (!result) {
            errorMessage.append("Invalid gift certificate description=").append(description).append(".");
        }
        return result;
    }

    private boolean checkGiftCertificationPrice(BigDecimal price, StringBuilder errorMessage) {
        boolean result = GiftCertificateValidator.isPriceValid(price);
        if (!result) {
            errorMessage.append("Invalid gift certificate price=").append(price).append(".");
        }
        return result;
    }

    private boolean checkGiftCertificationDuration(int duration, StringBuilder errorMessage) {
        boolean result = GiftCertificateValidator.isDurationValid(duration);
        if (!result) {
            errorMessage.append("Invalid gift certificate duration=").append(duration).append(".");
        }
        return result;
    }

    private boolean checkTagName(String tagName, StringBuilder errorMessage) {
        boolean result = TagValidator.isNameValid(tagName);
        if (!result) {
            errorMessage.append("Invalid tag name=").append(tagName).append(".");
        }
        return result;
    }

    private void checkCreateDate(LocalDateTime createDate, StringBuilder errorMessage) {
        boolean result = GiftCertificateValidator.isCreateDateValid(createDate);
        if (result) {
            errorMessage.append("Invalid gift certificate createDate.");
        }
    }

    private void checkLastUpdateDate(LocalDateTime lastUpdateDate, StringBuilder errorMessage) {
        boolean result = GiftCertificateValidator.isLastUpdateDateValid(lastUpdateDate);
        if (result) {
            errorMessage.append("Invalid gift certificate lastUpdateDate.");
        }
    }

    private boolean checkSqlOrderBy(String sortType, StringBuilder errorMessage) {
        boolean result = SqlValidator.ORDER_BY(sortType);
        if (!result) {
            errorMessage.append("Invalid type of sort=").append(sortType).append(".");
        }
        return result;
    }
}
