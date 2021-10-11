package com.epam.esm.controller;


import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.certificate.GiftCertificateCreationDTOMapper;
import com.epam.esm.mapper.certificate.GiftCertificateDTOMapper;
import com.epam.esm.mapper.certificate.GiftCertificateResponseDTOMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validator.GiftCertificateCreationDTOValidator;
import com.epam.esm.validator.GiftCertificateDTOValidator;
import com.epam.esm.validator.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Gift certificates controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private final GiftCertificateService giftCertificateService;
    private final GiftCertificateCreationDTOMapper giftCertificateCreationDTOMapper;
    private final GiftCertificateDTOMapper giftCertificateDTOMapper;
    private final GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper;

    /**
     * Init the gift certificates controller class.
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService,
                                     GiftCertificateCreationDTOMapper giftCertificateCreationDTOMapper,
                                     GiftCertificateDTOMapper giftCertificateDTOMapper,
                                     GiftCertificateResponseDTOMapper giftCertificateResponseDTOMapper) {
        this.giftCertificateService = giftCertificateService;
        this.giftCertificateCreationDTOMapper = giftCertificateCreationDTOMapper;
        this.giftCertificateDTOMapper = giftCertificateDTOMapper;
        this.giftCertificateResponseDTOMapper = giftCertificateResponseDTOMapper;
    }

    /**
     * * Finds all gift certificates by param
     *
     * @param tagName                        the name of certificate to be found.
     * @param partGiftCertificateName        the part of name of gift certificate to be found.
     * @param partGiftCertificateDescription the part of gift description of certificate to be found.
     * @param sortByName                     the sort param by gift certification name.
     * @param sortByCreateDate               the sort param by create date.
     * @param sortByLastUpdateDate           the sort param by create date.
     * @return list with found items.
     */
    @GetMapping
    public List<GiftCertificateResponseDTO> findGiftCertificates(@RequestParam(required = false) String tagName,
                                                                 @RequestParam(required = false) String partGiftCertificateName,
                                                                 @RequestParam(required = false) String partGiftCertificateDescription,
                                                                 @RequestParam(required = false) String sortByName,
                                                                 @RequestParam(required = false) String sortByCreateDate,
                                                                 @RequestParam(required = false) String sortByLastUpdateDate) {
        ParamValidator.isParamValid(tagName,partGiftCertificateName,partGiftCertificateDescription,sortByName,
                sortByCreateDate,sortByLastUpdateDate);
        return giftCertificateService.findGiftCertificateByIdWithTagsAndParams(tagName, partGiftCertificateName,
                        partGiftCertificateDescription, sortByName, sortByCreateDate, sortByLastUpdateDate).stream().
                map(giftCertificateResponseDTOMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Create a new gift certificate;
     *
     * @param giftCertificateCreationDTO an object that contain object request
     */
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addGiftCertificate(@RequestBody GiftCertificateCreationDTO giftCertificateCreationDTO) {
        GiftCertificateCreationDTOValidator.isGiftCertificateCreationDTOValid(giftCertificateCreationDTO);
        GiftCertificate giftCertificate = giftCertificateCreationDTOMapper.toGiftCertificate(giftCertificateCreationDTO);
        giftCertificateService.add(giftCertificate);
    }

    /**
     * Finds gift certificate by id
     *
     * @param id the id of gift certificate to be found.
     * @return found gift certificate object.
     */
    @GetMapping("/{id}")
    public GiftCertificateResponseDTO findGiftCertificateById(@PathVariable long id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return giftCertificateResponseDTOMapper.toDto(giftCertificate);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGiftCertificate(@PathVariable long id,
                                      @RequestBody GiftCertificateDTO giftCertificateDTO) {
        GiftCertificateDTOValidator.isGiftCertificateDTOValid(giftCertificateDTO);
        giftCertificateService.update(id, giftCertificateDTOMapper.toGiftCertificate(giftCertificateDTO));
    }
}