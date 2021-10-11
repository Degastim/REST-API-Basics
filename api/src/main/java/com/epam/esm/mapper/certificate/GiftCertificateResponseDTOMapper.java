package com.epam.esm.mapper.certificate;

import com.epam.esm.dto.certificate.GiftCertificateResponseDTO;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;

/**
 * GiftCertificate and GiftCertificateResponseDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class GiftCertificateResponseDTOMapper {
    /**
     * Maps a GiftCertificate as GiftCertificateResponseDTO.
     *
     * @param giftCertificate object object to map.
     * @return giftCertificate which contains the object after mapping.
     */
    public GiftCertificateResponseDTO toDto(GiftCertificate giftCertificate) {
        return new GiftCertificateResponseDTO(giftCertificate.getId(), giftCertificate.getName(),
                giftCertificate.getDescription(), giftCertificate.getPrice(), giftCertificate.getDuration(),
                giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(), giftCertificate.getTags());
    }
}
