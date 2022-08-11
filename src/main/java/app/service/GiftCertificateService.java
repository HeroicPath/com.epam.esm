package app.service;

import app.dto.GiftCertificateDto;
import app.dto.TagDto;
import app.mapper.GiftCertificateMapper;
import app.model.Tag;
import app.repository.GiftCertificateRepository;
import app.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftCertificateService {

    final GiftCertificateRepository giftCertificateRepository;
    final GiftCertificateMapper mapper;
    TagRepository tagRepository;

    public GiftCertificateService(GiftCertificateRepository repository, GiftCertificateMapper mapper) {
        this.giftCertificateRepository = repository;
        this.mapper = mapper;
    }

    public List<GiftCertificateDto> getAll() {
        return giftCertificateRepository.getAll().stream().map(a -> mapper.toDto(a)).collect(Collectors.toList());
    }

    public GiftCertificateDto get(Integer id) {
        return mapper.toDto(giftCertificateRepository.get(id));
    }

    public void create(GiftCertificateDto dto) {
        tagRepository.create(new TagDto(dto.getDescription()));
        giftCertificateRepository.create(dto);
    }

    public void update(GiftCertificateDto dto, Integer id) {
        if (!get(id).getDescription().equals(dto.getDescription())) {
            tagRepository.create(new TagDto(dto.getDescription()));
        }
        giftCertificateRepository.update(dto, id);
    }

    public void delete(Integer id) {
        giftCertificateRepository.delete(id);
    }
}
