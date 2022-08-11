package app.repository.impl;

import app.dto.TagDto;
import app.model.Tag;
import app.repository.TagRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepositoryImpl implements TagRepository {

    final JdbcTemplate template;

    public TagRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Tag> getAll() {
        return template.query("SELECT * FROM tags", new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag get(Integer id) {
        return template.query("SELECT * FROM tags WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Tag.class))
                .stream().findAny().orElse(null);
        //        TODO create an exception
    }

    @Override
    public void create(TagDto tag) {
        template.update("INSERT INTO tags VALUES(DEFAULT, ?)",
                tag.getName());
    }

    @Override
    public void delete(Integer id) {
        template.update("DELETE FROM tags WHERE id=?", id);
    }
}
