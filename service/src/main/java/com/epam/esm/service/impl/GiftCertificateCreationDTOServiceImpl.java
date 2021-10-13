package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.certificate.GiftCertificateCreationDTOMapper;
import com.epam.esm.mapper.certificate.GiftCertificateResponseDTOMapper;
import com.epam.esm.service.GiftCertificateCreationDTOService;
import com.epam.esm.validator.GiftCertificateCreationDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GiftCertificateCreationDTOServiceImpl implements GiftCertificateCreationDTOService {
    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;
    private final GiftCertificatesTagDao giftCertificatesTagDao;
    private final GiftCertificateCreationDTOValidator giftCertificateCreationDTOValidator;
    private final GiftCertificateCreationDTOMapper giftCertificateCreationDTOMapper;
    private final GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper;

    @Autowired
    public GiftCertificateCreationDTOServiceImpl(GiftCertificateDao giftCertificateDao,
                                                 TagDao tagDao,
                                                 GiftCertificatesTagDao giftCertificatesTagDao,
                                                 GiftCertificateCreationDTOValidator giftCertificateCreationDTOValidator,
                                                 GiftCertificateCreationDTOMapper giftCertificateCreationDTOMapper,
                                                 GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper) {
        this.giftCertificateCreationDTOValidator = giftCertificateCreationDTOValidator;
        this.giftCertificateCreationDTOMapper = giftCertificateCreationDTOMapper;
        this.giftCertificateResponseDTOMapper = giftCertificateResponseDTOMapper;
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
        this.giftCertificatesTagDao = giftCertificatesTagDao;
    }

    @Override
    @Transactional
    public GiftCertificateResponseDTO add(GiftCertificateCreationDTO giftCertificateCreationDTO) {
        List<Tag> tagList = giftCertificateCreationDTO.getTags();
        giftCertificateCreationDTOValidator.isGiftCertificateCreationDTOValid(giftCertificateCreationDTO);
        GiftCertificate giftCertificate = giftCertificateCreationDTOMapper.toGiftCertificate(giftCertificateCreationDTO);
        LocalDateTime localDateTime = LocalDateTime.now();
        giftCertificate.setCreateDate(localDateTime);
        giftCertificate.setLastUpdateDate(localDateTime);
        giftCertificate = giftCertificateDao.add(giftCertificate);
        long giftCertificateId = giftCertificate.getId();
        if (tagList != null) {
            for (Tag tag : tagList) {
                long tagId = tag.getId();
                long addTagId = tagId;
                String tagName = tag.getName();
                if (tagId != 0) {
                    if (tagName != null) {
                        tagDao.addWithId(tag);
                    }
                } else {
                    Tag newTag = tagDao.addWithoutId(tag);
                    addTagId = newTag.getId();
                    tag.setId(addTagId);
                }
                giftCertificatesTagDao.add(giftCertificateId, addTagId);
            }
        }
        giftCertificate.setTags(tagList);
        return giftCertificateResponseDTOMapper.toDto(giftCertificate);
    }
}
