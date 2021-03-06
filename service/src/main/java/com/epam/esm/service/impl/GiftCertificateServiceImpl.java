package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.GiftCertificateDTOMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validator.GiftCertificateDTOValidator;
import com.epam.esm.validator.PaginationContainerValidator;
import com.epam.esm.validator.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * GiftCertificateService implementation.
 *
 * @author Yauheni Tsitov
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao giftCertificateDao;
    private final GiftCertificateDTOMapper giftCertificateDTOMapper;
    private final GiftCertificateDTOValidator giftCertificateDTOValidator;
    private final TagDao tagDao;
    private final ParamValidator paramValidator;
    private final PaginationContainerValidator paginationContainerValidator;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao,
                                      GiftCertificateDTOMapper giftCertificateDTOMapper,
                                      GiftCertificateDTOValidator giftCertificateDTOValidator,
                                      TagDao tagDao,
                                      ParamValidator paramValidator,
                                      PaginationContainerValidator paginationContainerValidator) {
        this.giftCertificateDao = giftCertificateDao;
        this.giftCertificateDTOMapper = giftCertificateDTOMapper;
        this.giftCertificateDTOValidator = giftCertificateDTOValidator;
        this.tagDao = tagDao;
        this.paramValidator = paramValidator;
        this.paginationContainerValidator = paginationContainerValidator;
    }

    @Override
    @Transactional
    public GiftCertificateDTO findById(long id) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.GIFT_CERTIFICATE));
        return giftCertificateDTOMapper.toDTO(giftCertificate);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        GiftCertificate giftCertificate = giftCertificateOptional.orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.GIFT_CERTIFICATE));
        giftCertificateDao.delete(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO update(long id, GiftCertificateDTO giftCertificateDTO) {
        giftCertificateDTO.setId(id);
        giftCertificateDTOValidator.isGiftCertificateDTOUpdateValid(giftCertificateDTO);
        GiftCertificate newGiftCertificate = giftCertificateDTOMapper.toEntity(giftCertificateDTO);
        processGiftCertificateTags(newGiftCertificate);
        newGiftCertificate = giftCertificateDao.update(newGiftCertificate);
        return giftCertificateDTOMapper.toDTO(newGiftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO add(GiftCertificateDTO giftCertificateCreationDTO) {
        giftCertificateDTOValidator.isGiftCertificateDTOAddValid(giftCertificateCreationDTO);
        GiftCertificate giftCertificate = giftCertificateDTOMapper.toEntity(giftCertificateCreationDTO);
        processGiftCertificateTags(giftCertificate);
        giftCertificateDao.add(giftCertificate);
        return giftCertificateDTOMapper.toDTO(giftCertificate);
    }

    @Override
    @Transactional
    public List<GiftCertificateDTO> findGiftCertificateByIdWithTagsAndParams(PaginationContainer paginationContainer,
                                                                             ParamContainer paramContainer) {
        paginationContainerValidator.paginationPaginationContainer(paginationContainer);
        paramValidator.isParamValid(paramContainer);
        List<GiftCertificate> giftCertificateList = giftCertificateDao.executeSqlSelect(paramContainer, paginationContainer);
        return giftCertificateList.stream().map(giftCertificateDTOMapper::toDTO).collect(Collectors.toList());
    }

    private void processGiftCertificateTags(GiftCertificate giftCertificate) {
        Set<Tag> newTags = giftCertificate.getTags();
        Set<Tag> tags = new HashSet<>();
        if (newTags != null) {
            for (Tag newTag : newTags) {
                String tagName = newTag.getName();
                Optional<Tag> daoTagOptional = tagDao.findByName(tagName);
                if (daoTagOptional.isPresent()) {
                    Tag daoTag = daoTagOptional.get();
                    tags.add(daoTag);
                } else {
                    tags.add(newTag);
                }
            }
        }
        giftCertificate.setTags(tags);
    }
}
