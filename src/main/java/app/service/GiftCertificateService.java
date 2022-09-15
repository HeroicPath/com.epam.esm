package app.service;

import app.dto.GiftCertificateDto;
import app.dto.TagDto;
import app.mapper.GiftCertificateMapper;
import app.model.GiftCertificate;
import app.model.Tag;
import app.repository.GiftCertificateRepository;
import app.repository.TagRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @Transactional
public class GiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;
    private final GiftCertificateMapper mapper;
    private final TagService tagService;
    private final TagRepository tagRepository;
    private final JdbcTemplate template;

    public GiftCertificateService(GiftCertificateRepository giftCertificateRepository, GiftCertificateMapper mapper, TagService tagService, TagRepository tagRepository, JdbcTemplate template) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.mapper = mapper;
        this.tagService = tagService;
        this.tagRepository = tagRepository;
        this.template = template;
    }

    public List<GiftCertificateDto> getAll() {
        List<GiftCertificate> list = giftCertificateRepository.getAll();
        return list.stream().map(gc -> toDtoWithTags(gc,gc.getId())).collect(Collectors.toList());
    }

    public GiftCertificateDto get(Integer id) {
        GiftCertificate gc = giftCertificateRepository.get(id);
        return toDtoWithTags(gc, id);
    }

    public GiftCertificateDto getByName(String name) {
        GiftCertificate gc = giftCertificateRepository.getByName(name);
        return toDtoWithTags(gc, gc.getId());
    }

    public GiftCertificateDto getByDescription(String description) {
        GiftCertificate gc = giftCertificateRepository.getByDescription(description);
        return toDtoWithTags(gc, gc.getId());
    }

    public void create(GiftCertificateDto dto, TagDto tagDto) {
        if (tagDto != null) {
            Tag tag = tagRepository.getByName(tagDto.getName());
            if(tag == null){
                tagService.create(tagDto);
                tag = tagRepository.getByName(tagDto.getName());
            }
            List<Tag> tags = new ArrayList<>();
            tags.add(tag);
            dto.setTags(tags);
        }
        giftCertificateRepository.create(dto);
    }

    public void update(GiftCertificateDto dto, Integer id, TagDto tagDto) {
        if (!contains(getTags(id), tagDto)){
            Tag tag = tagRepository.getByName(tagDto.getName());
            if (tag == null){
                tagService.create(tagDto);
                tag = tagRepository.getByName(tagDto.getName());
            }
            giftCertificateRepository.update(dto, id, tag);
        }
        giftCertificateRepository.update(dto, id, null);
    }

    public void delete(Integer id) {
        giftCertificateRepository.delete(id);
    }

    public boolean contains(List<Tag> list, TagDto dto) {
        boolean contains = false;
        String newName = dto.getName();
        for (Tag tag : list){
            if (tag.getName().equals(newName)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public List<Tag> getTags (Integer id){
        List<Integer> tagIds = template.queryForList("SELECT tag_id FROM gift_certificates_tags WHERE gc_id=?", Integer.class, id);
        return tagIds.stream().map(tagRepository::get).collect(Collectors.toList());
    }

    public GiftCertificateDto toDtoWithTags(GiftCertificate giftCertificate, Integer id) {
        GiftCertificateDto dto = mapper.toDto(giftCertificate);
        List<Tag> tags = getTags(id);
        dto.setTags(tags);
        return dto;
    }
}
