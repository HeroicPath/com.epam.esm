package com.epam.esm.app.service.impl;

import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.exception.LocalException;
import com.epam.esm.app.mapper.GiftCertificateMapper;
import com.epam.esm.app.model.GiftCertificate;
import com.epam.esm.app.model.Tag;
import com.epam.esm.app.repository.GiftCertificateRepository;
import com.epam.esm.app.repository.TagRepository;
import com.epam.esm.app.service.GiftCertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;
    private final GiftCertificateMapper giftCertificateMapper;
    private final TagServiceImpl tagService;
    private final TagRepository tagRepository;
    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository, GiftCertificateMapper mapper, TagServiceImpl tagService, TagRepository tagRepository, JdbcTemplate template) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.giftCertificateMapper = mapper;
        this.tagService = tagService;
        this.tagRepository = tagRepository;
        this.jdbcTemplate = template;
    }

    @Override
    public GiftCertificateDto getById(Integer id) {
        GiftCertificate gc = giftCertificateRepository.getById(id);
        return toDtoWithTags(gc, id);
    }

    @Override
    public GiftCertificateDto getByStringParameter(String parameter, String parameterType) {
        GiftCertificate gc = giftCertificateRepository.getByStringParameter(parameter, parameterType);
        return toDtoWithTags(gc, gc.getId());
    }

    @Override
    public List<GiftCertificateDto> getByTagName(String tagName) {
        List<GiftCertificate> gcList = giftCertificateRepository.getByTagName(tagName);
        return gcList.stream().map((gc -> toDtoWithTags(gc, gc.getId()))).collect(Collectors.toList());
    }

    @Override
    public void create(GiftCertificateDto giftCertificateDto, TagDto tagDto) {
        validateGiftCertificate(giftCertificateDto);
        if (tagDto != null) {
            if (!tagRepository.isPresent(tagDto.getName())) {
                tagService.create(tagDto);
            }
            List<Tag> tags = new ArrayList<>();
            tags.add(tagRepository.getByName(tagDto.getName()));
            giftCertificateDto.setTags(tags);
        }
        giftCertificateRepository.create(giftCertificateDto);
    }

    @Override
    public void update(GiftCertificateDto giftCertificateDto, Integer id, TagDto tagDto) {
        validateGiftCertificate(giftCertificateDto);
        if (tagDto != null && !containsTheTag(getTags(id), tagDto)) {
            if (!tagRepository.isPresent(tagDto.getName())) {
                tagService.create(tagDto);
            }
            Tag tag = tagRepository.getByName(tagDto.getName());
            giftCertificateRepository.update(giftCertificateDto, id, tag);
            return;
        }
        giftCertificateRepository.update(giftCertificateDto, id, null);
    }

    @Override
    public void deleteById(Integer id) {
        giftCertificateRepository.deleteById(id);
    }

    public boolean containsTheTag(List<Tag> list, TagDto dto) {
        boolean contains = false;
        String newName = dto.getName();
        for (Tag tag : list) {
            if (tag.getName().equals(newName)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public List<Tag> getTags(Integer id) {
        List<Integer> tagIds = jdbcTemplate.queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, id);
        return tagIds
                .stream()
                .map(tagRepository::getById)
                .collect(Collectors.toList());
    }

    public GiftCertificateDto toDtoWithTags(GiftCertificate giftCertificate, Integer id) {
        GiftCertificateDto dto = giftCertificateMapper.toDto(giftCertificate);
        List<Tag> tags = getTags(id);
        dto.setTags(tags);
        return dto;
    }

    private void validateGiftCertificate(GiftCertificateDto giftCertificateDto) {
        if (giftCertificateDto.getName() == null || giftCertificateDto.getName().length() > 30 || giftCertificateDto.getName().trim().length() < 1
                || giftCertificateDto.getDescription() == null) {
            throw new LocalException(HttpStatus.BAD_REQUEST, "Name should be provided and should be 1 to 30 characters in length. Description should be provided");
        }
    }
}
