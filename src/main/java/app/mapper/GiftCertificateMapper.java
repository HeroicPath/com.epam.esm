package app.mapper;

import app.dto.GiftCertificateDto;
import app.model.GiftCertificate;
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
