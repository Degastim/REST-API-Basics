package com.epam.esm.service;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;

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
    List<TagDTO> findAll(PaginationContainer paginationContainer);

    /**
     * Adds new tag.
     *
     * @param tagDTO an object to be added.
     * @return TagResponseDTO object for response
     */
    TagDTO add(TagDTO tagDTO);

    /**
     * Finds tag by id.
     *
     * @param id of the object to be found.
     * @return found tag object
     */
    TagDTO findById(long id);

    /**
     * Deletes tag.
     *
     * @param id of the object to be deleted.
     */
    void delete(long id);

    TagDTO findMostWidelyTagUsersHighestCostOrders();
}