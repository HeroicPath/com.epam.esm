package com.epam.esm.app.repository;

import com.epam.esm.app.TestConfig;
import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.model.GiftCertificate;
import com.epam.esm.app.model.Tag;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {TestConfig.class},
        loader = AnnotationConfigContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GiftCertificateRepositoryTest {

    @Autowired
    GiftCertificateRepository giftCertificateRepository;

    @Autowired
    TagRepository tagRepository;

    @Test
    @Order(1)
    void getByIdTest() {
        GiftCertificate byId = giftCertificateRepository.getById(1);

        assertThat(byId).isNotNull();
    }

    @Test
    @Order(2)
    void getByStringParameterTest() {
        GiftCertificate byName = giftCertificateRepository.getByStringParameter("name", "name");
        GiftCertificate byDescription = giftCertificateRepository.getByStringParameter("description", "description");

        assertThat("name").isEqualTo(byName.getName());
        assertThat("description").isEqualTo(byDescription.getDescription());
    }

    @Test
    @Order(4)
    void getByTagNameTest() {
        List<GiftCertificate> giftCertificateList = giftCertificateRepository.getByTagName("name");

        assertThat(giftCertificateList.isEmpty()).isFalse();
    }

    @Test
    @Order(3)
    void createTest() {
        List<Tag> tags = new ArrayList<>();
        tags.add(tagRepository.getById(1));
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto("newName", "newDescription", 1.1, 100, tags);

        giftCertificateRepository.create(giftCertificateDto);

        assertThat(giftCertificateRepository.getByStringParameter("newName", "name")).isNotNull();
    }

    @Test
    @Order(5)
    void updateTest() {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto("noname", "noDescription", 1.1, 100, null);

        String firstName = giftCertificateRepository.getById(1).getName();
        giftCertificateRepository.update(giftCertificateDto, 1, null);
        String updatedName = giftCertificateRepository.getById(1).getName();

        assertThat(firstName).isNotEqualTo(updatedName);
    }

    @Test
    @Order(6)
    void deleteByIdTest() {
        giftCertificateRepository.deleteById(1);

        assertThatThrownBy(() -> giftCertificateRepository.getById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}