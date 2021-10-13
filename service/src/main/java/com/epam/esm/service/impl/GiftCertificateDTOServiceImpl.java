package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.certificate.GiftCertificateDTOMapper;
import com.epam.esm.service.GiftCertificateDTOService;
import com.epam.esm.validator.GiftCertificateDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GiftCertificateDTOServiceImpl implements GiftCertificateDTOService {
    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;
    private final GiftCertificatesTagDao giftCertificatesTagDao;
    private final GiftCertificateDTOValidator giftCertificateDTOValidator;
    private final GiftCertificateDTOMapper giftCertificateDTOMapper;

    @Autowired
    public GiftCertificateDTOServiceImpl(GiftCertificateDao giftCertificateDao,
                                         TagDao tagDao,
                                         GiftCertificatesTagDao giftCertificatesTagDao,
                                         GiftCertificateDTOValidator giftCertificateDTOValidator,
                                         GiftCertificateDTOMapper giftCertificateDTOMapper) {
        this.tagDao = tagDao;
        this.giftCertificateDTOValidator = giftCertificateDTOValidator;
        this.giftCertificateDTOMapper = giftCertificateDTOMapper;
        this.giftCertificateDao = giftCertificateDao;
        this.giftCertificatesTagDao = giftCertificatesTagDao;

    }

    @Override
    @Transactional
    public void update(long id, GiftCertificateDTO giftCertificateDTO) {
        giftCertificateDTOValidator.isGiftCertificateDTOValid(giftCertificateDTO);
        GiftCertificate newGiftCertificate = giftCertificateDTOMapper.toGiftCertificate(giftCertificateDTO);
        List<Tag> newTags = newGiftCertificate.getTags();
        newGiftCertificate.setId(id);
        giftCertificateDao.update(newGiftCertificate);
        if (newTags != null) {
            giftCertificatesTagDao.deleteByGiftCertificateId(id);
            for (Tag newTag : newTags) {
                long tagId = newTag.getId();
                String tagName = newTag.getName();
                long addTagId = tagId;
                if (tagId != 0) {
                    if (tagName != null) {
                        if (tagDao.findById(tagId).isPresent()) {
                            tagDao.update(newTag);
                        } else {
                            tagDao.addWithId(newTag);
                        }
                    }
                } else {
                    Tag tag = tagDao.addWithoutId(newTag);
                    addTagId = tag.getId();
                }
                giftCertificatesTagDao.add(id, addTagId);
            }
        }
    }
}
