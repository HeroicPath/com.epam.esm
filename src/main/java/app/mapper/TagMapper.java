package app.mapper;

import app.dto.TagDto;
import app.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagDto toDto(Tag tag) {
        TagDto dto = new TagDto();

        dto.setName(tag.getName());

        return dto;
    }
}
