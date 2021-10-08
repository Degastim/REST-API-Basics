package com.epam.esm.controller;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @param tagName                        the name of certificate to be found.
     * @param partGiftCertificateName        the part of name of gift certificate to be found.
     * @param partGiftCertificateDescription the part of gift description of certificate to be found.
     * @param sortByName                     the sort param by gift certification name.
     * @param sortByCreateDate               the sort param by create date.
     * @param sortByLastUpdateDate           the sort param by create date.
     * @return list with found items.
     */
    @GetMapping
    public List<GiftCertificate> findGiftCertificates(@RequestParam(required = false) String tagName,
                                                      @RequestParam(required = false) String partGiftCertificateName,
                                                      @RequestParam(required = false) String partGiftCertificateDescription,
                                                      @RequestParam(required = false) String sortByName,
                                                      @RequestParam(required = false) String sortByCreateDate,
                                                      @RequestParam(required = false) String sortByLastUpdateDate) {
        return giftCertificateService.findGiftCertificateByIdWithTagsAndParams(tagName, partGiftCertificateName, partGiftCertificateDescription, sortByName, sortByCreateDate, sortByLastUpdateDate);
    }

    /**
     * Create a new gift certificate;
     *
     * @param giftCertificate an object to be created
     * @return ResponseEntity object with response status and some information about creating.
     */
    @PostMapping
    public ResponseEntity<String> addGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        giftCertificateService.add(giftCertificate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Gift certificate added successfully");
    }

    /**
     * Finds gift certificate by id
     *
     * @param id the id of gift certificate to be found.
     * @return found gift certificate object.
     */
    @GetMapping("/{id}")
    public GiftCertificate findGiftCertificateById(@PathVariable long id) {
        return giftCertificateService.findById(id);
    }

    /**
     * Deletes gift certificate.
     *
     * @param id the id of gift certificate to be deleted.
     * @return Response entity with removal information.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiftCertificateByID(@PathVariable long id) {
        giftCertificateService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Gift certificate deleted successfully");
    }

    /**
     * Updates gift certificate.
     *
     * @param id the id of gift certificate to be updated.
     * @return Response entity with updating information.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateGiftCertificate(@PathVariable long id, @RequestBody GiftCertificate newGiftCertificate) {
        giftCertificateService.update(id, newGiftCertificate);
        return ResponseEntity.status(HttpStatus.OK).body("Gift certificate updated successfully");
    }
}