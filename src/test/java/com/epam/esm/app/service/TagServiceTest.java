package com.epam.esm.app.service;

import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.exception.LocalException;
import com.epam.esm.app.mapper.GiftCertificateMapper;
import com.epam.esm.app.mapper.TagMapper;
import com.epam.esm.app.model.Tag;
import com.epam.esm.app.repository.GiftCertificateRepository;
import com.epam.esm.app.repository.TagRepository;
import com.epam.esm.app.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagMapper tagMapper;
    @Mock
    private GiftCertificateMapper giftCertificateMapper;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private GiftCertificateRepository giftCertificateRepository;
    private Tag tag;
    private TagDto tagDto;

    @InjectMocks
    private TagServiceImpl tagService;

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setId(1);
        tag.setName("tagName");

        tagDto = new TagDto();
        tagDto.setName("tagName");

    }

    @Test
    void getByIdTest() {
        Mockito.when(tagRepository.getById(1)).thenReturn(tag);
        Mockito.when(tagMapper.toDto(tag)).thenReturn(tagDto);

        tagService.getById(1);

        Mockito.verify(tagRepository).getById(1);
        Mockito.verify(tagMapper).toDto(tag);
    }

    @Test
    void createTest() {
        tagService.create(tagDto);

        Mockito.verify(tagRepository).create(tagDto);

        assertThatThrownBy(() -> {
            tagDto.setName(null);
            tagService.validateTagDto(tagDto);
        }).isInstanceOf(LocalException.class)
                .hasMessageContaining("Provide a name!");
    }

    @Test
    void deleteByIdTest() {
        tagService.deleteById(1);

        Mockito.verify(tagRepository).deleteById(1);
    }
}