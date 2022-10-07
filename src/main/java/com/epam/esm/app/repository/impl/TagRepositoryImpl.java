package com.epam.esm.app.repository.impl;

import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.model.Tag;
import com.epam.esm.app.repository.TagRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final JdbcTemplate jdbcTemplate;

    public TagRepositoryImpl(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public Tag getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tags WHERE id=?", new BeanPropertyRowMapper<>(Tag.class), id);
    }

    @Override
    public Tag getByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM tags WHERE name=?", new BeanPropertyRowMapper<>(Tag.class), name);
    }

    @Override
    public void create(TagDto tagDto) {
        jdbcTemplate.update("INSERT INTO tags (name) VALUES (?)", tagDto.getName());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM gift_certificates_tags WHERE tag_id=?", id);
        jdbcTemplate.update("DELETE FROM tags WHERE id=?", id);
    }

    @Override
    public boolean isPresent(String name){
        List<String> tagNames = jdbcTemplate.queryForStream("SELECT * FROM tags", new BeanPropertyRowMapper<>(Tag.class)).map(Tag::getName).collect(Collectors.toList());
        return tagNames.contains(name);
    }
}
