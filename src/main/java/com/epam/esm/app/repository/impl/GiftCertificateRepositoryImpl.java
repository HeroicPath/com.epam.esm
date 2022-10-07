package com.epam.esm.app.repository.impl;

import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.model.GiftCertificate;
import com.epam.esm.app.model.Tag;
import com.epam.esm.app.repository.GiftCertificateRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM gift_certificates WHERE id=?", new BeanPropertyRowMapper<>(GiftCertificate.class), id);
    }

    @Override
    public GiftCertificate getByStringParameter(String parameter, String parameterType) {
        String sql = "SELECT * FROM gift_certificates WHERE " + parameterType + " = ?";
        if (isPresentByParameter(parameter, parameterType)) {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(GiftCertificate.class), parameter);
        } else {
            return likeCallByParameter(parameter, parameterType);
        }
    }

    @Override
    public List<GiftCertificate> getByTagName(String tagName) {
        Integer tagId = jdbcTemplate.queryForObject("SELECT id FROM tags WHERE name = ?", Integer.class, tagName);
        List<Integer> idList = jdbcTemplate.queryForList("SELECT gc_id FROM gift_certificates_tags WHERE tag_id=?", Integer.class, tagId);
        return idList.stream().map(this::getById).collect(Collectors.toList());
    }

    @Override
    public void create(GiftCertificateDto giftCertificate) {
        jdbcTemplate.update("INSERT INTO gift_certificates VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)",
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                LocalDateTime.now(),
                LocalDateTime.now());

        List<Tag> tags = giftCertificate.getTags();
        if (tags == null) return;
        stitchById(tags.get(0), getByStringParameter(giftCertificate.getName(), "name"));
    }

    @Override
    public void update(GiftCertificateDto giftCertificateDto, Integer id, Tag tag) {
        jdbcTemplate.update("UPDATE gift_certificates SET name=?, description=?, price=?, duration=?, last_update_date=? WHERE id=?",
                giftCertificateDto.getName(),
                giftCertificateDto.getDescription(),
                giftCertificateDto.getPrice(),
                giftCertificateDto.getDuration(),
                LocalDateTime.now(),
                id);

        if (tag != null) {
            stitchById(tag, id);
        }
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM gift_certificates_tags WHERE gc_id=?", id);
        jdbcTemplate.update("DELETE FROM gift_certificates WHERE id=?", id);
    }

    public void stitchById(Tag tag, GiftCertificate giftCertificate) {
        jdbcTemplate.update("INSERT INTO gift_certificates_tags(gc_id, tag_id) VALUES (?, ?)",
                giftCertificate.getId(),
                tag.getId());
    }

    public void stitchById(Tag tag, Integer giftCertificateId) {
        jdbcTemplate.update("INSERT INTO gift_certificates_tags(gc_id, tag_id) VALUES (?, ?)",
                giftCertificateId,
                tag.getId());
    }

    public boolean isPresentByParameter(String parameter, String parameterType) {
        List<String> parameters = jdbcTemplate.queryForStream("SELECT * FROM gift_certificates", new BeanPropertyRowMapper<>(GiftCertificate.class)).map(parameterType.equals("name") ? GiftCertificate::getName : GiftCertificate::getDescription).collect(Collectors.toList());
        return parameters.contains(parameter);
    }

    public GiftCertificate likeCallByParameter(String parameter, String parameterType){
        String sql = "SELECT * FROM gift_certificates WHERE " + parameterType + " LIKE ? ORDER BY name, create_date";
        List<GiftCertificate> resultList = jdbcTemplate.queryForStream(sql, new BeanPropertyRowMapper<>(GiftCertificate.class), "%" + parameter + "%").collect(Collectors.toList());
        boolean isEmpty = resultList.isEmpty();
        return isEmpty ? null : resultList.get(0);
    }
}
