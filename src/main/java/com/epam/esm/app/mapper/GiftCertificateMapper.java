package com.epam.esm.app.mapper;

import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.model.GiftCertificate;
import org.springframework.stereotype.Component;

@Component
public class GiftCertificateMapper {

    public GiftCertificateDto toDto(GiftCertificate giftCertificate) {
        GiftCertificateDto dto = new GiftCertificateDto();

        dto.setDescription(giftCertificate.getDescription());
        dto.setDuration(giftCertificate.getDuration());
        dto.setName(giftCertificate.getName());
        dto.setPrice(giftCertificate.getPrice());
        dto.setTags(giftCertificate.getTagList());

        return dto;
    }
}
