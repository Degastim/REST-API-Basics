package com.epam.esm.service;

import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;

/**
 * Interface provides actions on gift certificates.
 *
 * @author Yauheni Tsitov
 */
public interface GiftCertificateService {

    /**
     * Finds gift certificate by id.
     *
     * @param id of the object to be found.
     * @return found object of gift certificate
     */
    GiftCertificateResponseDTO findById(long id);

    /**
     * Deletes gift certificate.
     *
     * @param id of the object to be deleted.
     */
    void delete(long id);
}