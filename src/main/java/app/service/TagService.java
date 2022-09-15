package app.service;

import app.dto.TagDto;
import app.mapper.GiftCertificateMapper;
import app.mapper.TagMapper;
import app.model.GiftCertificate;
import app.model.Tag;
import app.repository.GiftCertificateRepository;
import app.repository.TagRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @Transactional
public class TagService {

    private final TagRepository repository;
    private final TagMapper mapper;
    private final GiftCertificateMapper gcMapper;
    private final JdbcTemplate template;
    private final GiftCertificateRepository giftCertificateRepository;

    public TagService(TagRepository repository, TagMapper mapper, GiftCertificateMapper gcMapper, JdbcTemplate template, GiftCertificateRepository giftCertificateRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.gcMapper = gcMapper;
        this.template = template;
        this.giftCertificateRepository = giftCertificateRepository;
    }

    public List<TagDto> getAll() {
        List<Tag> list = repository.getAll();
        return list.stream().map(tag -> toDtoWithGiftCertificateDtos(tag, tag.getId())).collect(Collectors.toList());
    }

    public TagDto get(Integer id) {
        Tag tag = repository.get(id);
        return toDtoWithGiftCertificateDtos(tag, id);
    }

    public TagDto getByName(String name) {
        Tag tag = repository.getByName(name);
        return toDtoWithGiftCertificateDtos(tag, tag.getId());
    }

    public void create(TagDto tag) {
        if (tag.getName().trim().isEmpty()) {
            return;
        }
        repository.create(tag);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

    public List<GiftCertificate> getGiftCertificates(Integer id) {
        List<Integer> idList = template.queryForList("SELECT gc_id FROM gift_certificates_tags WHERE tag_id=?", Integer.class, id);
        return idList.stream().map(giftCertificateRepository::get).collect(Collectors.toList());
    }

    public TagDto toDtoWithGiftCertificateDtos(Tag tag, Integer id){
        TagDto dto = mapper.toDto(tag);
        List<GiftCertificate> list = getGiftCertificates(id);
        dto.setGiftCertificateList(list.stream().map(gcMapper::toDto).collect(Collectors.toList()));
        return dto;
    }
}
