package com.epam.esm.service;

import com.epam.esm.dto.certificate.GiftCertificateDTO;

public interface GiftCertificateDTOService {
    /**
     * Updates gift certificate.
     *
     * @param id                 of the object to be updated.
     * @param giftCertificateDTO contain parameters for update.
     */
    void update(long id, GiftCertificateDTO giftCertificateDTO);
}
