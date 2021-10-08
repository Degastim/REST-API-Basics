package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    TagService tagService;

    /**
     * Init the tags controller class.
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Find all tags
     */
    @GetMapping
    public List<Tag> findAllTags() {
        return tagService.findAll();
    }

    /**
     * Add tag to database
     *
     * @param tag tan object to be created.
     * @return ResponseEntity object with response status and some information about creating.
     */
    @PostMapping
    public ResponseEntity<String> addTag(@RequestBody Tag tag) {
        tagService.add(tag);
        return ResponseEntity.status(HttpStatus.OK).body("Tag added successfully");
    }

    /**
     * Add tag to database
     *
     * @param id long value of tag id.
     * @return Tag.
     */
    @GetMapping("/{id}")
    public Tag findById(@PathVariable long id) {
        return tagService.findById(id);
    }

    /**
     * Delete tag from database
     *
     * @param id of tag to delete.
     * @return ResponseEntity object with response status and some information about deleting.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        tagService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tag deleted successfully");
    }
}

