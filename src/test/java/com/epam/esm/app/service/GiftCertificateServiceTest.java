package com.epam.esm.app.service;

import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.mapper.GiftCertificateMapper;
import com.epam.esm.app.model.GiftCertificate;
import com.epam.esm.app.model.Tag;
import com.epam.esm.app.repository.GiftCertificateRepository;
import com.epam.esm.app.repository.TagRepository;
import com.epam.esm.app.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.app.service.impl.TagServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceTest {

    @Mock
    private GiftCertificateRepository giftCertificateRepository;
    @Mock
    private GiftCertificateMapper giftCertificateMapper;
    @Mock
    private TagServiceImpl tagService;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    private final ArrayList<Integer> tagIds = new ArrayList<>();
    private final ArrayList<GiftCertificate> gcList = new ArrayList<>();
    private Tag tag;
    private GiftCertificate giftCertificate;
    private GiftCertificateDto giftCertificateDto;
    private TagDto tagDto;

    @BeforeEach
    void setUp() {
        giftCertificate = new GiftCertificate(
                1, "name", "description", 1.1, 1, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>()
        );
        giftCertificateDto = new GiftCertificateDto(
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration(),
                giftCertificate.getTagList()
        );
        tag = new Tag(1, "newTagName", new ArrayList<>());
        tagDto = new TagDto("tagName", tag.getGiftCertificates());
    }

    @Test
    void getById() {
        when(giftCertificateRepository.getById(1)).thenReturn(giftCertificate);
        when(giftCertificateMapper.toDto(giftCertificate)).thenReturn(giftCertificateDto);
        when(jdbcTemplate.queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1)).thenReturn(tagIds);

        giftCertificateService.getById(1);

        verify(giftCertificateRepository).getById(1);
        verify(giftCertificateMapper).toDto(giftCertificate);
        verify(jdbcTemplate).queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1);
    }

    @Test
    void getByStringParameter() {
        when(giftCertificateRepository.getByStringParameter("name", "name")).thenReturn(giftCertificate);
        when(giftCertificateRepository.getByStringParameter("description", "description")).thenReturn(giftCertificate);
        when(giftCertificateMapper.toDto(giftCertificate)).thenReturn(giftCertificateDto);
        when(jdbcTemplate.queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1)).thenReturn(tagIds);

        giftCertificateService.getByStringParameter("name", "name");
        giftCertificateService.getByStringParameter("description", "description");

        verify(giftCertificateRepository).getByStringParameter("name", "name");
        verify(giftCertificateRepository).getByStringParameter("description", "description");
        verify(giftCertificateMapper, atLeast(2)).toDto(giftCertificate);
        verify(jdbcTemplate, atLeast(2)).queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1);
    }

    @Test
    void getByTagName() {
        gcList.add(giftCertificate);
        when(giftCertificateRepository.getByTagName("tagName")).thenReturn(gcList);
        when(giftCertificateMapper.toDto(giftCertificate)).thenReturn(giftCertificateDto);
        when(jdbcTemplate.queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1)).thenReturn(tagIds);

        giftCertificateService.getByTagName("tagName");

        verify(giftCertificateRepository).getByTagName("tagName");
        verify(giftCertificateMapper).toDto(giftCertificate);
        verify(jdbcTemplate).queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1);
    }

    @Test
    void create() {
        when(tagRepository.isPresent("tagName")).thenReturn(true);

        giftCertificateService.create(giftCertificateDto, tagDto);
        giftCertificateService.create(giftCertificateDto, null);

        when(tagRepository.isPresent("tagName")).thenReturn(false);

        giftCertificateService.create(giftCertificateDto, tagDto);

        verify(giftCertificateRepository, atLeast(3)).create(giftCertificateDto);
        verify(tagService).create(tagDto);
        verify(tagRepository, atLeast(2)).isPresent("tagName");
        verify(tagRepository, atLeast(2)).getByName("tagName");

        Assertions.assertThatThrownBy(() -> {
            giftCertificateDto.setName(null);
            giftCertificateService.create(giftCertificateDto, null);
        });
    }

    @Test
    void update() {
        tagIds.add(1);
        when(jdbcTemplate.queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1)).thenReturn(tagIds);
        when(tagRepository.isPresent("tagName")).thenReturn(true);
        when(tagRepository.getByName("tagName")).thenReturn(tag);
        when(tagRepository.getById(1)).thenReturn(tag);

        giftCertificateService.update(giftCertificateDto, 1, tagDto);
        giftCertificateService.update(giftCertificateDto, 1, null);

        when(tagRepository.isPresent("tagName")).thenReturn(false);

        giftCertificateService.update(giftCertificateDto, 1, tagDto);

        tag.setName("tagName");

        giftCertificateService.update(giftCertificateDto, 1, tagDto);

        verify(giftCertificateRepository, atLeast(2)).update(giftCertificateDto, 1, tag);
        verify(giftCertificateRepository, atLeast(2)).update(giftCertificateDto, 1, null);
        verify(tagRepository, atLeast(2)).getByName("tagName");
        verify(tagRepository, atLeast(2)).isPresent("tagName");
        verify(tagRepository, atLeast(3)).getById(1);
        verify(tagService).create(tagDto);
        verify(jdbcTemplate, atLeast(3)).queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, 1);
    }

    @Test
    void deleteById() {
        giftCertificateService.deleteById(1);

        verify(giftCertificateRepository).deleteById(1);
    }
}