package com.epam.esm.service;

import com.epam.esm.dto.ParamContainer;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface ParamContainerService {

    /**
     * Finds gift certificates by param.
     *
     * @param paramContainer contain parameters for found gift certificate
     * @return list with found items.
     */
    List<GiftCertificateResponseDTO> findGiftCertificateByIdWithTagsAndParams(ParamContainer paramContainer);
}
