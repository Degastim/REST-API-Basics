package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with gift certificates and tags table.
 *
 * @author Yauheni Tstiov
 */
public interface TagDao {
    /**
     * Adds tag to the table.
     *
     * @param tag object to be added.
     */
    void add(Tag tag);

    /**
     * Finds all the tags.
     *
     * @return list with found tags
     */
    List<Tag> findAll();

    /**
     * Finds the tag by name.
     *
     * @param name of the object to be found.
     * @return optional object with found tag.
     */
    Optional<Tag> findByName(String name);

    /**
     * Finds the tag by id.
     *
     * @param id of the object to be found.
     * @return optional object with found tag.
     */
    Optional<Tag> findById(long id);

    /**
     * Deletes tag from the table.
     *
     * @param tag of the object to be deleted.
     */
    void delete(Tag tag);

    /**
     * Find the most widely used tag of a user with the highest cost of all orders.
     *
     * @return optional which contains tag.
     */
    Optional<Tag> findMostWidelyTagUsersHighestCostOrders();
}
