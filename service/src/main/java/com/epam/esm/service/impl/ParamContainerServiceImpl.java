package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.ParamContainer;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.mapper.certificate.GiftCertificateResponseDTOMapper;
import com.epam.esm.service.ParamContainerService;
import com.epam.esm.validator.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParamContainerServiceImpl implements ParamContainerService {
    private final GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper;
    private final GiftCertificateDao giftCertificateDao;
    private final ParamValidator paramValidator;

    @Autowired
    public ParamContainerServiceImpl(GiftCertificateDao giftCertificateDao,
                                     GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper,
                                     ParamValidator paramValidator) {
        this.giftCertificateDao = giftCertificateDao;
        this.giftCertificateResponseDTOMapper = giftCertificateResponseDTOMapper;
        this.paramValidator = paramValidator;
    }

    @Override
    public List<GiftCertificateResponseDTO> findGiftCertificateByIdWithTagsAndParams(ParamContainer paramContainer) {
        paramValidator.isParamValid(paramContainer);
        return giftCertificateDao.executeSqlSelect(paramContainer).stream().
                map(giftCertificateResponseDTOMapper::toDto).collect(Collectors.toList());
    }
}
