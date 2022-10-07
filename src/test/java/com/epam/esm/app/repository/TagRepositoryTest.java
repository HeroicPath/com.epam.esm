package com.epam.esm.app.repository;

import com.epam.esm.app.dto.GiftCertificateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TagRepositoryTest {

    private TagRepository tagRepository;
    private JdbcTemplate jdbcTemplate;
    private GiftCertificateDto giftCertificateDto;

    @BeforeEach
    void setUp(){
        giftCertificateDto = new GiftCertificateDto(
            "name",
            "description",
            1.99,
            10,
            null
        );
    }

    @Test
    void getById() {
        
    }

    @Test
    void create() {

    }

    @Test
    void deleteById() {
    }

    @Test
    void getByName() {
    }
}