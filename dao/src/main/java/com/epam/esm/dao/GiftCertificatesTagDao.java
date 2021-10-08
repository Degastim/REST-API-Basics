package com.epam.esm.dao;

/**
 * Interface used for interactions with gift certificates and tags table.
 *
 * @author Yauheni Tstiov
 */
public interface GiftCertificatesTagDao {
    /**
     * Adds pair of gift certificate id and tag id in database
     *
     * @param giftCertificateId value to be added.
     * @param tagId             object to be added.
     */
    void add(long giftCertificateId, long tagId);

    /**
     * Delete pair of gift certificate id and tag id from database by gift certificate id
     *
     * @param id value param for search.
     */
    void deleteByGiftCertificateId(long id);

    /**
     * Delete pair of gift certificate id and tag id from database by gift tag id
     *
     * @param id value param for search.
     */
    void deleteByTagId(long id);
}
