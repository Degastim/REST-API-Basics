package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

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
     */
    long add(GiftCertificate giftCertificate);

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
     * Updates the gift certificate.
     *
     * @param giftCertificate new object.
     */
    void update(GiftCertificate giftCertificate);

    /**
     * Execute custom sql command.
     *
     * @param sql contains sql command.
     */
    List<GiftCertificate> executeSql(StringBuilder sql);

    /**
     * Finds all the certificates.
     *
     * @return list with found certificates
     */
    List<GiftCertificate> findAll();

    /**
     * Finds the certificate by its name.
     *
     * @param name of the object to be found.
     * @return optional object with found certificate.
     */
    Optional<GiftCertificate> findByName(String name);
}
