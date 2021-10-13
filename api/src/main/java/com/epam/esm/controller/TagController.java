package com.epam.esm.controller;

import com.epam.esm.dto.tag.TagCreationDTO;
import com.epam.esm.dto.tag.TagResponseDTO;
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
    public List<TagResponseDTO> findAllTags() {
        return tagService.findAll();
    }

    /**
     * Add tag to database
     *
     * @param tagCreationDTO an object that contain object request
     */
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TagResponseDTO addTag(@RequestBody TagCreationDTO tagCreationDTO) {
        return tagService.add(tagCreationDTO);
    }

    /**
     * Add tag to database
     *
     * @param id long value of tag id.
     * @return TagDTO which contain information about the found tag.
     */
    @GetMapping("/{id}")
    public TagResponseDTO findById(@PathVariable long id) {
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
}

