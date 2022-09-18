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

    private final JdbcTemplate template;

    public TagRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Tag> getAll() {
        return template.query("SELECT * FROM tags", new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag getById(Integer id) {
        return template.queryForObject("SELECT * FROM tags WHERE id=?", new BeanPropertyRowMapper<>(Tag.class), id);
    }

    @Override
    public Tag getByName(String name) {
        return template.queryForObject("SELECT * FROM tags WHERE name=?", new BeanPropertyRowMapper<>(Tag.class), name);
    }

    @Override
    public void create(TagDto tag) {
        template.update("INSERT INTO tags (name) VALUES (?)", tag.getName());
    }

    @Override
    public void deleteById(Integer id) {
        template.update("DELETE FROM gift_certificates_tags WHERE tag_id=?", id);
        template.update("DELETE FROM tags WHERE id=?", id);
    }
}
