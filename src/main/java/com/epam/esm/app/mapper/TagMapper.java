package com.epam.esm.app.mapper;

import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagDto toDto(Tag tag) {
        TagDto dto = new TagDto();

        dto.setName(tag.getName());
        dto.setGiftCertificateList(tag.getGiftCertificates());

        return dto;
    }
}
