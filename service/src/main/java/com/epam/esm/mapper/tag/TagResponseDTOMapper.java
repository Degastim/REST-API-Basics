package com.epam.esm.mapper.tag;

import com.epam.esm.dto.tag.TagResponseDTO;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

/**
 * Tag and TagResponseDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class TagResponseDTOMapper {
    /**
     * Maps a Tag as TagResponseDTO.
     *
     * @param tag object object to map.
     * @return giftCertificate which contains the object after mapping.
     */
    public TagResponseDTO toTagResponseDTO(Tag tag) {
        return new TagResponseDTO(tag.getId(), tag.getName());
    }
}
