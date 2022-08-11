package app.repository.impl;

import app.dto.GiftCertificateDto;
import app.model.GiftCertificate;
import app.repository.GiftCertificateRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GiftCertificate> getAll() {
        return jdbcTemplate.query("SELECT * FROM gift_certificates", new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public GiftCertificate get(Integer id) {
        return jdbcTemplate.query("SELECT * FROM gift_certificates WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(GiftCertificate.class))
                .stream().findAny().orElse(null);
//        TODO create an exception
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
    }

    @Override
    public void update(GiftCertificateDto giftCertificate, Integer id) {
        jdbcTemplate.update("UPDATE gift_certificates SET name=?, description=?, price=?, duration=?, last_update_date=? WHERE id=?",
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                LocalDateTime.now(),
                id);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM gift_certificates WHERE id=?", id);
    }
}
