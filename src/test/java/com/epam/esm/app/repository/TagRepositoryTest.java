package com.epam.esm.app.repository;

import com.epam.esm.app.TestConfig;
import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.model.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {TestConfig.class},
        loader = AnnotationConfigContextLoader.class)
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void getById() {
        Tag byId = tagRepository.getById(1);

        assertThat(byId.getId()).isEqualTo(1);
    }

    @Test
    void getByName() {
        Tag byName = tagRepository.getByName("name");

        assertThat(byName.getName()).isEqualTo("name");
    }

    @Test
    void create() {
        TagDto tagDto = new TagDto("newName", null);

        tagRepository.create(tagDto);
        Tag byName = tagRepository.getByName("newName");

        assertThat(byName.getName()).isEqualTo("newName");
    }

    @Test
    void deleteById() {
        tagRepository.deleteById(2);

        assertThatThrownBy(() -> tagRepository.getById(2)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void isPresent() {
        assertTrue(tagRepository.isPresent("name"));
        assertFalse(tagRepository.isPresent("noname"));
    }
}