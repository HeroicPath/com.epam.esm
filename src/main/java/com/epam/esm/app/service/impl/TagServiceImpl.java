package com.epam.esm.app.service.impl;

import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.exception.LocalException;
import com.epam.esm.app.mapper.GiftCertificateMapper;
import com.epam.esm.app.mapper.TagMapper;
import com.epam.esm.app.model.GiftCertificate;
import com.epam.esm.app.model.Tag;
import com.epam.esm.app.repository.GiftCertificateRepository;
import com.epam.esm.app.repository.TagRepository;
import com.epam.esm.app.service.TagService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final GiftCertificateMapper giftCertificateMapper;
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateRepository giftCertificateRepository;

    public TagServiceImpl(@Qualifier("tagRepositoryImpl") TagRepository repository, TagMapper mapper, GiftCertificateMapper gcMapper, JdbcTemplate template, GiftCertificateRepository giftCertificateRepository) {
        this.tagRepository = repository;
        this.tagMapper = mapper;
        this.giftCertificateMapper = gcMapper;
        this.jdbcTemplate = template;
        this.giftCertificateRepository = giftCertificateRepository;
    }

    @Override
    public TagDto getById(Integer id) {
        Tag tag = tagRepository.getById(id);
        return toDtoWithGiftCertificateDtos(tag, id);
    }

    @Override
    public void create(TagDto tagDto) {
        validateTagDto(tagDto);
        tagRepository.create(tagDto);
    }

    @Override
    public void deleteById(Integer id) {
        tagRepository.deleteById(id);
    }

    public List<GiftCertificate> getGiftCertificates(Integer id) {
        List<Integer> idList = jdbcTemplate.queryForList("SELECT gc_id FROM gift_certificates_tags WHERE tag_id=?", Integer.class, id);
        return idList.stream().map(giftCertificateRepository::getById).collect(Collectors.toList());
    }

    public TagDto toDtoWithGiftCertificateDtos(Tag tag, Integer id){
        TagDto tagDto = tagMapper.toDto(tag);
        List<GiftCertificate> list = getGiftCertificates(id);
        tagDto.setGiftCertificateList(list.stream().map(giftCertificateMapper::toDto).collect(Collectors.toList()));
        return tagDto;
    }

    public void validateTagDto(TagDto tagDto) {
        if (tagDto.getName() == null || tagDto.getName().trim().length() < 1) {
            throw new LocalException(HttpStatus.BAD_REQUEST, "Provide a name!");
        }
    }
}
