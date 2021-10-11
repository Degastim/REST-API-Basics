package com.epam.esm.controller;

import com.epam.esm.dto.tag.TagCreationDTO;
import com.epam.esm.dto.tag.TagResponseDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.tag.TagCreationDTOMapper;
import com.epam.esm.mapper.tag.TagResponseDTOMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagCreationDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tags controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagResponseDTOMapper tagResponseDTOMapper;
    private final TagCreationDTOMapper tagCreationDTOMapper;

    /**
     * Init the tags controller class.
     */
    @Autowired
    public TagController(TagService tagService, TagResponseDTOMapper tagResponseDTOMapper, TagCreationDTOMapper tagCreationDTOMapper) {
        this.tagService = tagService;
        this.tagResponseDTOMapper = tagResponseDTOMapper;
        this.tagCreationDTOMapper = tagCreationDTOMapper;
    }

    /**
     * Find all tags
     *
     * @return list with TagDTO which contain information about the found tags.
     */
    @GetMapping
    public List<TagResponseDTO> findAllTags() {
        List<Tag> tagList = tagService.findAll();
        return tagList.stream().map(tagResponseDTOMapper::toTagResponseDTO).collect(Collectors.toList());
    }

    /**
     * Add tag to database
     *
     * @param tagCreationDTO an object that contain object request
     */
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addTag(@RequestBody TagCreationDTO tagCreationDTO) {
        TagCreationDTOValidator.isTagCreationDTOValid(tagCreationDTO);
        tagService.add(tagCreationDTOMapper.toTag(tagCreationDTO));
    }

    /**
     * Add tag to database
     *
     * @param id long value of tag id.
     * @return TagDTO which contain information about the found tag.
     */
    @GetMapping("/{id}")
    public TagResponseDTO findById(@PathVariable long id) {
        Tag tag = tagService.findById(id);
        return tagResponseDTOMapper.toTagResponseDTO(tag);
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

