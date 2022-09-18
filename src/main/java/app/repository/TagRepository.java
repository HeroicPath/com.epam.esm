package app.repository;


import app.dto.TagDto;
import app.model.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> getAll();

    Tag getById(Integer id);

    void create(TagDto tag);

    void deleteById(Integer id);

    Tag getByName(String name);
}
