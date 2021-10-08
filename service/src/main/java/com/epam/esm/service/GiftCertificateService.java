package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

/**
 * Interface provides actions on gift certificates.
 *
 * @author Yauheni Tsitov
 */
public interface GiftCertificateService {
    /**
     * Adds new gift certificate.
     *
     * @param giftCertificate an object to be added.
     */
    void add(GiftCertificate giftCertificate);

    /**
     * Finds gift certificate by id.
     *
     * @param id of the object to be found.
     * @return found object of gift certificate
     */
    GiftCertificate findById(long id);

    /**
     * Deletes gift certificate.
     *
     * @param id of the object to be deleted.
     */
    void delete(long id);

    /**
     * Updates gift certificate.
     *
     * @param id                 of the object to be updated.
     * @param newGiftCertificate new object.
     */
    void update(long id, GiftCertificate newGiftCertificate);

    /**
     * Finds gift certificates by param.
     *
     * @param tagName                        the name of certificate to be found.
     * @param partGiftCertificateName        the part of name of gift certificate to be found.
     * @param partGiftCertificateDescription the part of gift description of certificate to be found.
     * @param sortByName                     the sort param by gift certification name.
     * @param sortByCreateDate               the sort param by create date.
     * @param sortByLastUpdateDate           the sort param by create date.
     * @return list with found items.
     */
    List<GiftCertificate> findGiftCertificateByIdWithTagsAndParams(String tagName, String partGiftCertificateName, String partGiftCertificateDescription, String sortByName, String sortByCreateDate, String sortByLastUpdateDate);
}