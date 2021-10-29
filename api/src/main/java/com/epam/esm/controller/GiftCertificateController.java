package com.epam.esm.controller;


import com.epam.esm.dto.ParamContainer;
import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.service.GiftCertificateCreationDTOService;
import com.epam.esm.service.GiftCertificateDTOService;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.ParamContainerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ParamContainerService paramContainerService;
    private final GiftCertificateCreationDTOService giftCertificateCreationDTOService;
    private final GiftCertificateDTOService giftCertificateDTOService;

    /**
     * Init the gift certificates controller class.
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService, ParamContainerService paramContainerService,
                                     GiftCertificateCreationDTOService giftCertificateCreationDTOService,
                                     GiftCertificateDTOService giftCertificateDTOService) {
        this.giftCertificateService = giftCertificateService;
        this.paramContainerService = paramContainerService;
        this.giftCertificateCreationDTOService = giftCertificateCreationDTOService;
        this.giftCertificateDTOService = giftCertificateDTOService;
    }

    /**
     * * Finds all gift certificates by param
     *
     * @param paramContainer contain param for find gift certificate
     * @return list with found items.
     */
    @GetMapping
    public List<GiftCertificateResponseDTO> findGiftCertificates(@ModelAttribute ParamContainer paramContainer) {
        return paramContainerService.findGiftCertificateByIdWithTagsAndParams(paramContainer);
    }

    /**
     * Create a new gift certificate;
     *
     * @param giftCertificateCreationDTO an object that contain object request
     */
    @PostMapping
    public GiftCertificateResponseDTO addGiftCertificate(@RequestBody GiftCertificateCreationDTO giftCertificateCreationDTO) {
        return giftCertificateCreationDTOService.add(giftCertificateCreationDTO);
    }

    /**
     * Finds gift certificate by id
     *
     * @param id the id of gift certificate to be found.
     * @return found gift certificate object.
     */
    @GetMapping("/{id}")
    public GiftCertificateResponseDTO findGiftCertificateById(@PathVariable long id) {
        return giftCertificateService.findById(id);
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
    public GiftCertificateResponseDTO updateGiftCertificate(@PathVariable long id,
                                                            @RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateDTOService.update(id, giftCertificateDTO);
    }
}