package com.epam.esm.dao;

import com.epam.esm.dto.ParamContainer;
import com.epam.esm.entity.GiftCertificate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with gift certificates table.
 *
 * @author Yauheni Tstiov
 */
public interface GiftCertificateDao {
    /**
     * Adds gift certificate to the database.
     *
     * @param giftCertificate object to be added.
     * @return giftСertificate which was created.
     */
    GiftCertificate add(GiftCertificate giftCertificate);

    /**
     * Deletes gift certificate from the database.
     *
     * @param id of the object to be deleted.
     */
    void delete(long id);

    /**
     * Finds the certificate by id.
     *
     * @param id of the object to be found.
     * @return optional object with found certificate.
     */
    Optional<GiftCertificate> findById(long id);

    /**
     * Execute custom sql update command.
     *
     * @param giftCertificate contain parameters for update gift certificate.
     */
    void update(GiftCertificate giftCertificate);

    /**
     * Execute custom sql select command.
     *
     * @param paramContainer contain parameters for found gift certificate.
     */
    List<GiftCertificate> executeSqlSelect(ParamContainer paramContainer);

    /**
     * Finds all the certificates.
     *
     * @return list with found certificates
     */
    List<GiftCertificate> findAll();

    /**
     * Finds the certificate by its name,description,price,duration.
     *
     * @param name        of the object to be found.
     * @param description of the object to be found.
     * @param price       of the object to be found.
     * @param duration    of the object to be found
     * @return optional object with found certificate.
     */
    Optional<GiftCertificate> findByNameAndDescriptionAndPriceAndDuration(String name, String description,
                                                                          BigDecimal price, Integer duration);
}
