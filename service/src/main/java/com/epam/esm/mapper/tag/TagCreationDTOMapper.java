package com.epam.esm.mapper.tag;

import com.epam.esm.dto.tag.TagCreationDTO;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

/**
 * Tag and TagCreationDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class TagCreationDTOMapper {
    /**
     * Maps a TagCreationDTO as Tag.
     *
     * @param tagCreationDTO object object to map.
     * @return giftCertificate which contains the object after mapping.
     */
    public Tag toTag(TagCreationDTO tagCreationDTO) {
        return new Tag(tagCreationDTO.getId(), tagCreationDTO.getName());
    }
}
