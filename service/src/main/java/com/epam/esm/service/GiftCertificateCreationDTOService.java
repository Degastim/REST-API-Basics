package com.epam.esm.service;

import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;

public interface GiftCertificateCreationDTOService {
    /**
     * Adds new gift certificate.
     *
     * @param giftCertificateCreationDTO an object to be added.
     */
    GiftCertificateResponseDTO add(GiftCertificateCreationDTO giftCertificateCreationDTO);
}
