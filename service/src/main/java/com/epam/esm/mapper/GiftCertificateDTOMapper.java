package com.epam.esm.mapper;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * GiftCertificate and GiftCertificateDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class GiftCertificateDTOMapper implements DTOMapper<GiftCertificate, GiftCertificateDTO> {
    private final TagDTOMapper tagDTOMapper;

    public GiftCertificateDTOMapper(TagDTOMapper tagDTOMapper) {
        this.tagDTOMapper = tagDTOMapper;
    }

    @Override
    public GiftCertificate toEntity(GiftCertificateDTO giftCertificateDTO) {
        Set<TagDTO> tagDTOSet = giftCertificateDTO.getTags();
        Set<Tag> tagSet = null;
        if (tagDTOSet != null) {
            tagSet = tagDTOSet.stream().map(tagDTOMapper::toEntity).collect(Collectors.toSet());
        }
        return new GiftCertificate(giftCertificateDTO.getId(),
                giftCertificateDTO.getName(),
                giftCertificateDTO.getDescription(),
                giftCertificateDTO.getPrice(),
                giftCertificateDTO.getDuration(),
                tagSet);
    }

    @Override
    public GiftCertificateDTO toDTO(GiftCertificate giftCertificate) {
        Set<Tag> tagSet = giftCertificate.getTags();
        Set<TagDTO> tagDTOSet = null;
        if (tagSet != null) {
            tagDTOSet = tagSet.stream().map(tagDTOMapper::toDTO).collect(Collectors.toSet());
        }
        return new GiftCertificateDTO(giftCertificate.getId(),
                giftCertificate.getGiftCertificateName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                tagDTOSet);
    }
}
