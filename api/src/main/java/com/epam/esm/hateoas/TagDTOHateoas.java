package com.epam.esm.hateoas;

import com.epam.esm.controller.TagController;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Converter tag according to HATEOAS rules
 *
 * @author Yauheni Tstiov
 */
@Component
public class TagDTOHateoas {
    /**
     * Converts the tagDTO according to the rules of HATEOAS.
     *
     * @param tagDTO contains a tag for conversion.
     */
    public void build(TagDTO tagDTO) {
        long tagDTOId = tagDTO.getId();
        Link self = linkTo(methodOn(TagController.class).findById(tagDTOId)).withSelfRel();
        tagDTO.add(self);
    }

    /**
     * Converts the tag list according to the rules of HATEOAS.
     *
     * @param tagDTOList contains a tag for conversion.
     */
    public CollectionModel<TagDTO> build(List<TagDTO> tagDTOList, PaginationContainer paginationContainer) {
        for (TagDTO tagDTO : tagDTOList) {
            build(tagDTO);
        }
        Link self = linkTo(methodOn(TagController.class).findAllTags(paginationContainer)).withSelfRel();
        return CollectionModel.of(tagDTOList, self);
    }
}
