package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.TagController;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Set<TagDTO> tagDTOs = giftCertificateDTO.getTags();
        if (tagDTOs != null) {
            for (TagDTO tagDTO : tagDTOs) {
                long tagDTOId = tagDTO.getId();
                Link selfTagDTOLink = linkTo(methodOn(TagController.class).findById(tagDTOId)).withSelfRel();
                tagDTO.add(selfTagDTOLink);
            }
        }
        giftCertificateDTO.add(self);
    }

    /**
     * Converts the certificate according to the rules of HATEOAS.
     *
     * @param giftCertificateDTOList contains a gift certificate for conversion.
     */
    public CollectionModel<GiftCertificateDTO> build(List<GiftCertificateDTO> giftCertificateDTOList,
                                                     PaginationContainer paginationContainer,
                                                     ParamContainer paramContainer) {
        for (GiftCertificateDTO giftCertificateDTO : giftCertificateDTOList) {
            build(giftCertificateDTO);
        }
        Link self = linkTo(methodOn(GiftCertificateController.class).findGiftCertificates(paginationContainer,
                paramContainer)).withSelfRel();
        Link findAllTagsLink = linkTo(methodOn(TagController.class).findAllTags(null)).withRel("tags");
        return CollectionModel.of(giftCertificateDTOList, self, findAllTagsLink);
    }

}
