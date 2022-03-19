package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.param.ParamContainer;
import com.epam.esm.hateoas.GiftCertificateDTOHateoas;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    private final GiftCertificateDTOHateoas giftCertificateDTOHateoas;

    /**
     * Init the gift certificates controller class.
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService,
                                     GiftCertificateDTOHateoas giftCertificateDTOHateoas) {
        this.giftCertificateService = giftCertificateService;
        this.giftCertificateDTOHateoas = giftCertificateDTOHateoas;
    }

    /**
     * Searches for certificates by the required parameters and with pagination.
     *
     * @param paramContainer      contain param for find gift certificate.
     * @param paginationContainer contains the desired page and the number of elements per page.
     * @return list with found items by HATEOAS.
     */
    @GetMapping
    public CollectionModel<GiftCertificateDTO> findGiftCertificates(@ModelAttribute PaginationContainer paginationContainer,
                                                                    @ModelAttribute ParamContainer paramContainer) {
        List<GiftCertificateDTO> list = giftCertificateService.findGiftCertificateByIdWithTagsAndParams(paginationContainer,
                paramContainer);
        return giftCertificateDTOHateoas.build(list, paginationContainer, paramContainer);
    }

    /**
     * Create a new gift certificate.
     *
     * @param giftCertificateCreationDTO an object that contain object request.
     * @return gift certificate added to the database by HATEOAS.
     */
    @PreAuthorize("hasAuthority('certificates:create')")
    @PostMapping
    public GiftCertificateDTO addGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateCreationDTO) {
        GiftCertificateDTO giftCertificateDTO = giftCertificateService.add(giftCertificateCreationDTO);
        giftCertificateDTOHateoas.build(giftCertificateDTO);
        return giftCertificateDTO;
    }

    /**
     * Finds gift certificate by id
     *
     * @param id the id of gift certificate to be found.
     * @return the found gift certificate object by HATEOAS.
     */
    @GetMapping("/{id}")
    public GiftCertificateDTO findGiftCertificateById(@PathVariable long id) {
        GiftCertificateDTO giftCertificateDTO = giftCertificateService.findById(id);
        giftCertificateDTOHateoas.build(giftCertificateDTO);
        return giftCertificateDTO;
    }

    /**
     * Deletes gift certificate by gift certificate id.
     *
     * @param id the id of gift certificate to be deleted.
     */
    @PreAuthorize("hasAuthority('certificates:delete')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGiftCertificateByID(@PathVariable long id) {
        giftCertificateService.delete(id);
    }

    /**
     * Updates gift certificate.
     *
     * @param id the id of gift certificate to be updated.
     * @return an updated gift certificate by HATEOAS.
     */
    @PreAuthorize("hasAuthority('certificates:update')")
    @PatchMapping("/{id}")
    public GiftCertificateDTO updateGiftCertificate(@PathVariable long id,
                                                    @RequestBody GiftCertificateDTO giftCertificateDTO) {
        GiftCertificateDTO result = giftCertificateService.update(id, giftCertificateDTO);
        giftCertificateDTOHateoas.build(result);
        return result;
    }
}