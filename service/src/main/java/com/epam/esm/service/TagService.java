package com.epam.esm.service;

import com.epam.esm.dto.tag.TagCreationDTO;
import com.epam.esm.dto.tag.TagResponseDTO;

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
    List<TagResponseDTO> findAll();

    /**
     * Adds new tag.
     *
     * @param tagCreationDTO an object to be added.
     * @return TagResponseDTO object for response
     */
    TagResponseDTO add(TagCreationDTO tagCreationDTO);

    /**
     * Finds tag by id.
     *
     * @param id of the object to be found.
     * @return found tag object
     */
    TagResponseDTO findById(long id);

    /**
     * Deletes tag.
     *
     * @param id of the object to be deleted.
     */
    void delete(long id);
}