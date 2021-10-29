package com.epam.esm.service;

import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;

public interface GiftCertificateDTOService {
    /**
     * Updates gift certificate.
     *
     * @param id                 of the object to be updated.
     * @param giftCertificateDTO contain parameters for update.
     */
    GiftCertificateResponseDTO update(long id, GiftCertificateDTO giftCertificateDTO);
}
