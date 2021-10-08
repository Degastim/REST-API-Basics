package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * TagService implementation.
 *
 * @author Yauheni Tsitov
 */
@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    private final GiftCertificatesTagDao giftCertificatesTagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao, GiftCertificatesTagDao giftCertificatesTagDao) {
        this.tagDao = tagDao;
        this.giftCertificatesTagDao = giftCertificatesTagDao;
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> tagList = tagDao.findAll();
        if (tagList.size() != 0) {
            return tagList;
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (All)");
        }
    }

    @Override
    @Transactional
    public void add(Tag tag) {
        String tagName = tag.getName();
        Optional<Tag> daoTag = tagDao.findByName(tagName);
        if (daoTag.isPresent()) {
            throw new InvalidFieldValueException("There is already a tag with the same name.");
        }
        if (TagValidator.isNameValid(tagName)) {
            tagDao.add(tag);
        } else {
            throw new InvalidFieldValueException("Invalid tag name.");
        }

    }

    @Override
    public Tag findById(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if (tagOptional.isPresent()) {
            return tagOptional.get();
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if (tagOptional.isPresent()) {
            giftCertificatesTagDao.deleteByTagId(id);
            tagDao.delete(id);
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id);
        }
    }
}