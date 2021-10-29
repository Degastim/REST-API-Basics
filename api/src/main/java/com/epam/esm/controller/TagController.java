package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * Init the tags controller class.
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Find all tags
     *
     * @return list with TagDTO which contain information about the found tags.
     */
    @GetMapping
    public List<TagDTO> findAllTags(@ModelAttribute PaginationContainer paginationContainer) {
        return tagService.findAll(paginationContainer);
    }

    /**
     * Add tag to database
     *
     * @param tagDTO an object that contain object request
     */
    @PostMapping
    public TagDTO addTag(@RequestBody TagDTO tagDTO) {
        return tagService.add(tagDTO);
    }

    /**
     * Add tag to database
     *
     * @param id long value of tag id.
     * @return TagDTO which contain information about the found tag.
     */
    @GetMapping("/{id}")
    public TagDTO findById(@PathVariable long id) {
        return tagService.findById(id);
    }

    /**
     * Delete tag from database
     *
     * @param id of tag to delete.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) {
        tagService.delete(id);
    }

    @GetMapping("/mostWidelyTagUsersHighestCostOrders")
    public TagDTO findMostWidelyTagUsersHighestCostOrders() {
        return tagService.findMostWidelyTagUsersHighestCostOrders();
    }
}

