package app.repository.impl;

import app.dto.GiftCertificateDto;
import app.model.GiftCertificate;
import app.model.Tag;
import app.repository.GiftCertificateRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GiftCertificate> getAll() {
        return jdbcTemplate.query("SELECT * FROM gift_certificates ORDER BY name ASC", new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public GiftCertificate get(Integer id) {
        return jdbcTemplate.query("SELECT * FROM gift_certificates WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(GiftCertificate.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public GiftCertificate getByName(String name) {
        Optional<GiftCertificate> giftCertificate = jdbcTemplate.query("SELECT * FROM gift_certificates WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(GiftCertificate.class))
                .stream().findAny();
        return giftCertificate.orElseGet(() -> jdbcTemplate.query("SELECT * FROM gift_certificates WHERE name LIKE ?", new Object[]{"%" + name + "%"}, new BeanPropertyRowMapper<>(GiftCertificate.class))
                .stream().findAny().orElse(null));
    }

    @Override
    public GiftCertificate getByDescription(String description) {
        Optional<GiftCertificate> giftCertificate = jdbcTemplate.query("SELECT * FROM gift_certificates WHERE description=?", new Object[]{description}, new BeanPropertyRowMapper<>(GiftCertificate.class))
                .stream().findAny();
        return giftCertificate.orElseGet(() -> jdbcTemplate.query("SELECT * FROM gift_certificates WHERE description LIKE ?", new Object[]{"%" + description + "%"}, new BeanPropertyRowMapper<>(GiftCertificate.class))
                .stream().findAny().orElse(null));
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
        if (tags.isEmpty()) return;
        stitch(tags.get(0), getByName(giftCertificate.getName()));
    }

    @Override
    public void update(GiftCertificateDto giftCertificate, Integer id, Tag tag) {
        jdbcTemplate.update("UPDATE gift_certificates SET name=?, description=?, price=?, duration=?, last_update_date=? WHERE id=?",
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                LocalDateTime.now(),
                id);

        if (tag != null){
            stitch(tag, id);
        }
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM gift_certificates_tags WHERE gc_id=?", id);
        jdbcTemplate.update("DELETE FROM gift_certificates WHERE id=?", id);
    }

    public void stitch(Tag tag, GiftCertificate giftCertificate) {
        jdbcTemplate.update("INSERT INTO gift_certificates_tags(gc_id, tag_id) VALUES (?, ?)",
                giftCertificate.getId(),
                tag.getId());
    }

    public void stitch(Tag tag, Integer giftCertificateId) {
        jdbcTemplate.update("INSERT INTO gift_certificates_tags(gc_id, tag_id) VALUES (?, ?)",
                giftCertificateId,
                tag.getId());
    }
}
