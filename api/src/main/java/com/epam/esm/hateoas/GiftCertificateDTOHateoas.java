package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.TagController;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.dto.param.ParamContainer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Converter certificate according to HATEOAS rules
 *
 * @author Yauheni Tstiov
 */
@Component
public class GiftCertificateDTOHateoas {

    /**
     * Converts the certificate according to the rules of HATEOAS.
     *
     * @param giftCertificateDTO contains a gift certificate for conversion.
     */
    public void build(GiftCertificateDTO giftCertificateDTO) {
        Link self = linkTo(GiftCertificateController.class).slash(giftCertificateDTO.getId()).withSelfRel();
        Link delete = linkTo(methodOn(GiftCertificateController.class).findGiftCertificateById(giftCertificateDTO.getId())).withRel("delete");
        Link update = linkTo(methodOn(GiftCertificateController.class).updateGiftCertificate(giftCertificateDTO.getId(), giftCertificateDTO)).withRel("update");
        Link addTagLink = linkTo(methodOn(TagController.class).addTag(new TagDTO())).withRel("add_tag");
        Link findAllTagsLink = linkTo(methodOn(TagController.class).findAllTags(null)).withRel("find_all_tags");
        Set<TagDTO> tagDTOs = giftCertificateDTO.getTags();
        if (tagDTOs != null) {
            for (TagDTO tagDTO : tagDTOs) {
                long tagDTOId = tagDTO.getId();
                Link selfTagDTOLink = linkTo(methodOn(TagController.class).findById(tagDTOId)).withSelfRel();
                Link deleteTagLink = linkTo(methodOn(TagController.class).findById(tagDTOId)).withRel("delete_tag");
                tagDTO.add(selfTagDTOLink, deleteTagLink);
            }
        }
        giftCertificateDTO.add(self, delete, update, addTagLink, findAllTagsLink);
    }

    /**
     * Converts the certificate according to the rules of HATEOAS.
     *
     * @param giftCertificateDTOList contains a gift certificate for conversion.
     */
    public CollectionModel<GiftCertificateDTO> build(List<GiftCertificateDTO> giftCertificateDTOList, PaginationContainer paginationContainer, ParamContainer paramContainer) {
        for (GiftCertificateDTO giftCertificateDTO : giftCertificateDTOList) {
            build(giftCertificateDTO);
        }
        Link add = linkTo(methodOn(GiftCertificateController.class).findGiftCertificates(paginationContainer, paramContainer)).withRel("add");
        Link self = linkTo(methodOn(GiftCertificateController.class).findGiftCertificates(paginationContainer, paramContainer)).withSelfRel();
        Link findAllTagsLink = linkTo(methodOn(TagController.class).findAllTags(null)).withRel("find_all_tags");
        Link addTagLink = linkTo(methodOn(TagController.class).addTag(new TagDTO())).withRel("add_tag");
        return CollectionModel.of(giftCertificateDTOList, self, add, findAllTagsLink, addTagLink);
    }
}
