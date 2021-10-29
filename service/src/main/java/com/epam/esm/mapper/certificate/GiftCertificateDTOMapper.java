package com.epam.esm.mapper.certificate;

import com.epam.esm.dto.certificate.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;

/**
 * GiftCertificate and GiftCertificateDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class GiftCertificateDTOMapper {
    /**
     * Maps a giftCertificateDTO as GiftCertificate.
     *
     * @param giftCertificateDTO object object to map.
     * @return giftCertificate which contains the object after mapping.
     */
    public GiftCertificate toGiftCertificate(GiftCertificateDTO giftCertificateDTO) {
        return new GiftCertificate(giftCertificateDTO.getId(),
                giftCertificateDTO.getName(),
                giftCertificateDTO.getDescription(),
                giftCertificateDTO.getPrice(),
                giftCertificateDTO.getDuration(),
                giftCertificateDTO.getCreateDate(),
                giftCertificateDTO.getLastUpdateDate(),
                giftCertificateDTO.getTags());
    }
}
