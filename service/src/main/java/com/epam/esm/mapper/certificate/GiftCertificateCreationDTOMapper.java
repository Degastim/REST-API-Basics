package com.epam.esm.mapper.certificate;

import com.epam.esm.dto.certificate.GiftCertificateCreationDTO;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;

/**
 * GiftCertificate and giftCertificationCreationDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class GiftCertificateCreationDTOMapper {
    /**
     * Maps a GiftCertificateCreationDTO as GiftCertificate.
     *
     * @param giftCertificateCreationDTO object object to map.
     * @return giftCertificate which contains the object after mapping.
     */
    public GiftCertificate toGiftCertificate(GiftCertificateCreationDTO giftCertificateCreationDTO) {
        return new GiftCertificate(giftCertificateCreationDTO.getName(), giftCertificateCreationDTO.getDescription(),
                giftCertificateCreationDTO.getPrice(), giftCertificateCreationDTO.getDuration(),
                giftCertificateCreationDTO.getTags());
    }
}
