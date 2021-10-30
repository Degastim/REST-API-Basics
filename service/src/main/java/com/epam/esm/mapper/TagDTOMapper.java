package com.epam.esm.mapper;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

/**
 * Tag and TagCreationDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class TagDTOMapper implements DTOMapper<Tag, TagDTO> {

    public Tag toEntity(TagDTO tagDTO) {
        return new Tag(tagDTO.getId(), tagDTO.getName());
    }

    public TagDTO toDTO(Tag tag) {
        return new TagDTO(tag.getId(), tag.getName());
    }
}
