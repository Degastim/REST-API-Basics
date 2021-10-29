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
     * @return Tag which was created
     */
    Tag add(Tag tag);

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
     * Update tag from the table.
     *
     * @param tag to be changed.
     */
    void update(Tag tag);

    /**
     * Deletes tag from the table.
     *
     * @param id of the object to be deleted.
     */
    void delete(Tag tag);
    Tag findMostWidelyTagUsersHighestCostOrders();
}
