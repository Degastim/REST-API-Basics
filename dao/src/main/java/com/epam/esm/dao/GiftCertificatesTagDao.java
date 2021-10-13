package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificatesTag;

import java.util.Optional;

/**
 * Interface used for interactions with gift certificates and tags table.
 *
 * @author Yauheni Tstiov
 */
public interface GiftCertificatesTagDao {
    /**
     * Adds a pair of gift certificate id and tag id in database
     *
     * @param giftCertificateId value to be added.
     * @param tagId             object to be added.
     */
    void add(long giftCertificateId, long tagId);

    /**
     * Delete a pair of gift certificate id and tag id from database by gift certificate id
     *
     * @param id value param for search.
     */
    void deleteByGiftCertificateId(long id);

    /**
     * Delete a pair of gift certificate id and tag id from database by gift tag id
     *
     * @param id value param for search.
     */
    void deleteByTagId(long id);

    /**
     * Find a pair of gift certificate id and tag id from database by gift tag id
     *
     * @param giftCertificateId value param for search.
     * @param tagId             value param for search.
     * @return Optional<GiftCertificatesTag> may contain gift certificates tag
     */
    Optional<GiftCertificatesTag> findByGiftCertificateIdAndTagId(long giftCertificateId, long tagId);
}
