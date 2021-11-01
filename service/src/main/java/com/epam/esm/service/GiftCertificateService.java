package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.param.ParamContainer;

import java.util.List;

/**
 * Interface provides actions on gift certificates.
 *
 * @author Yauheni Tsitov
 */
public interface GiftCertificateService {
    /**
     * Updates gift certificate.
     *
     * @param id                 of the object to be updated.
     * @param giftCertificateDTO contain parameters for update.
     */
    GiftCertificateDTO update(long id, GiftCertificateDTO giftCertificateDTO);

    /**
     * Adds new gift certificate.
     *
     * @param giftCertificateCreationDTO an object to be added.
     */
    GiftCertificateDTO add(GiftCertificateDTO giftCertificateCreationDTO);

    /**
     * Finds gift certificate by id.
     *
     * @param id of the object to be found.
     * @return found object of gift certificate
     */
    GiftCertificateDTO findById(long id);

    /**
     * Deletes gift certificate.
     *
     * @param id of the object to be deleted.
     */
    void delete(long id);

    /**
     * Finds gift certificates by param.
     *
     * @param paramContainer contain parameters for found gift certificate
     * @return list with found items.
     */
    List<GiftCertificateDTO> findGiftCertificateByIdWithTagsAndParams(PaginationContainer paginationContainer,
                                                                      ParamContainer paramContainer);
}