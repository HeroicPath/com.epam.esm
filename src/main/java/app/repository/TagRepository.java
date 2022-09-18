package app.repository;


import app.dto.TagDto;
import app.model.Tag;

public interface TagRepository {

    Tag getById(Integer id);

    void create(TagDto tag);

    void deleteById(Integer id);

    Tag getByName(String name);
}
