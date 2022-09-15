package app.repository;


import app.dto.TagDto;
import app.model.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> getAll();

    Tag get(Integer id);

    void create(TagDto tag);

    void delete(Integer id);

    Tag getByName(String name);
}
