package com.epam.esm.controller;


import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Gift certificates controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;

    /**
     * Init the gift certificates controller class.
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * * Finds all gift certificates by param
     *
     * @param paramContainer contain param for find gift certificate
     * @return list with found items.
     */
    @GetMapping
    public CollectionModel<GiftCertificateDTO> findGiftCertificates(@ModelAttribute PaginationContainer paginationContainer, @ModelAttribute ParamContainer paramContainer) {
        List<GiftCertificateDTO> list = giftCertificateService.findGiftCertificateByIdWithTagsAndParams(paginationContainer, paramContainer);
        for (GiftCertificateDTO giftCertificateDTO : list) {
            WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findGiftCertificateById(giftCertificateDTO.getId()));
            Link self = builder.withSelfRel();
            Link delete = builder.withRel("delete");
            Link update = builder.withRel("update");
            giftCertificateDTO.add(self, delete, update);
        }
        Link add = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findGiftCertificates(paginationContainer, paramContainer)).withRel("add");
        Link self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findGiftCertificates(paginationContainer, paramContainer)).withSelfRel();
        return CollectionModel.of(list, add, self);
    }

    /**
     * Create a new gift certificate;
     *
     * @param giftCertificateCreationDTO an object that contain object request
     */
    @PostMapping
    public GiftCertificateDTO addGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateCreationDTO) {
        GiftCertificateDTO giftCertificateDTO = giftCertificateService.add(giftCertificateCreationDTO);
        Link self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findGiftCertificateById(giftCertificateDTO.getId())).withSelfRel();
        Link delete=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findGiftCertificateById(giftCertificateDTO.getId())).withRel("delete");
        giftCertificateDTO.add(self,delete);
        return giftCertificateDTO;
    }

    /**
     * Finds gift certificate by id
     *
     * @param id the id of gift certificate to be found.
     * @return found gift certificate object.
     */
    @GetMapping("/{id}")
    public GiftCertificateDTO findGiftCertificateById(@PathVariable long id) {
        GiftCertificateDTO giftCertificateDTO = giftCertificateService.findById(id);
        Link link = WebMvcLinkBuilder.linkTo(GiftCertificateController.class).slash(giftCertificateDTO.getId()).withSelfRel();
        giftCertificateDTO.add(link);
        return giftCertificateDTO;
    }

    /**
     * Deletes gift certificate.
     *
     * @param id the id of gift certificate to be deleted.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGiftCertificateByID(@PathVariable long id) {
        giftCertificateService.delete(id);
    }

    /**
     * Updates gift certificate.
     *
     * @param id the id of gift certificate to be updated.
     */
    @PatchMapping("/{id}")
    public GiftCertificateDTO updateGiftCertificate(@PathVariable long id,
                                                    @RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.update(id, giftCertificateDTO);
    }
}