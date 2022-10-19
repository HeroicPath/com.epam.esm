package com.epam.esm.app;

import com.epam.esm.app.repository.GiftCertificateRepository;
import com.epam.esm.app.repository.TagRepository;
import com.epam.esm.app.repository.impl.GiftCertificateRepositoryImpl;
import com.epam.esm.app.repository.impl.TagRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestConfig {

    @Bean
    public EmbeddedDatabase embeddedDatabase() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2).addDefaultScripts().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TagRepository tagRepository(JdbcTemplate jdbcTemplate) {
        return new TagRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public GiftCertificateRepository giftCertificateRepository(JdbcTemplate jdbcTemplate) {
        return new GiftCertificateRepositoryImpl(jdbcTemplate);
    }
}
