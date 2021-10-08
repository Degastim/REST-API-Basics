package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

/**
 * Interface provides actions on tags.
 *
 * @author Yauheni Tstiov
 */
public interface TagService {
    /**
     * Finds all tags.
     *
     * @return list of the found tags.
     */
    List<Tag> findAll();

    /**
     * Adds new tag.
     *
     * @param tag an object to be added.
     */
    void add(Tag tag);

    /**
     * Finds tag by id.
     *
     * @param id of the object to be found.
     * @return found tag object
     */
    Tag findById(long id);

    /**
     * Deletes tag.
     *
     * @param id of the object to be deleted.
     */
    void delete(long id);
}