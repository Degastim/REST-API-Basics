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
public class TagDTOMapper {
    /**
     * Maps a TagCreationDTO as Tag.
     *
     * @param tagDTO object object to map.
     * @return giftCertificate which contains the object after mapping.
     */
    public Tag toEntity(TagDTO tagDTO) {
        return new Tag(tagDTO.getId(), tagDTO.getName());
    }
    /**
     * Maps a Tag as TagDTO.
     *
     * @param tag object object to map.
     * @return giftCertificate which contains the object after mapping.
     */
    public TagDTO toDTO(Tag tag) {
        return new TagDTO(tag.getId(), tag.getName());
    }
}
