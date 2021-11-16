package com.epam.esm.controller;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.hateoas.TagDTOHateoas;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tags controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagDTOHateoas tagDTOHateoas;

    @Autowired
    public TagController(TagService tagService, TagDTOHateoas tagDTOHateoas) {
        this.tagService = tagService;
        this.tagDTOHateoas = tagDTOHateoas;
    }

    /**
     * Find all tags
     *
     * @param paginationContainer contains the desired page and the number of elements per page.
     * @return list with TagDTO which contain information about the found tags by HATEOAS.
     */
    @GetMapping
    public CollectionModel<TagDTO> findAllTags(@ModelAttribute PaginationContainer paginationContainer) {
        List<TagDTO> tagDTOList = tagService.findAll(paginationContainer);
        return tagDTOHateoas.build(tagDTOList, paginationContainer);
    }

    /**
     * Add tag to database.
     *
     * @param tagDTO an object that contain object request.
     * @return tag added to the database by HATEOAS.
     */
    @PreAuthorize("hasAuthority('tags:create')")
    @PostMapping
    public TagDTO addTag(@RequestBody TagDTO tagDTO) {
        TagDTO result = tagService.add(tagDTO);
        tagDTOHateoas.build(result);
        return result;
    }

    /**
     * Find tag by id.
     *
     * @param id long value of tag id.
     * @return TagDTO which contain information about the found tag by HATEOAS.
     */
    @GetMapping("/{id}")
    public TagDTO findById(@PathVariable long id) {
        TagDTO result = tagService.findById(id);
        tagDTOHateoas.build(result);
        return result;
    }

    /**
     * Delete tag from database
     *
     * @param id of tag to delete.
     */
    @PreAuthorize("hasAuthority('tags:delete')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) {
        tagService.delete(id);
    }

    /**
     * Find the most widely used tag of a user with the highest cost of all orders.
     *
     * @return TagDTO which contain information about the found tag by HATEOAS.
     */
    @GetMapping("/mostWidelyTagUsersHighestCostOrders")
    public TagDTO findMostWidelyTagUsersHighestCostOrders() {
        TagDTO result = tagService.findMostWidelyTagUsersHighestCostOrders();
        tagDTOHateoas.build(result);
        return result;
    }
}

