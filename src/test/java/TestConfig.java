import com.epam.esm.app.repository.GiftCertificateRepository;
import com.epam.esm.app.repository.TagRepository;
import com.epam.esm.app.repository.impl.GiftCertificateRepositoryImpl;
import com.epam.esm.app.repository.impl.TagRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:mem:default");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("username");
        dataSource.setPassword("password");

        return dataSource;
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
