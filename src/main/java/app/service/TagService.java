package app.service;

import app.dto.TagDto;
import app.mapper.TagMapper;
import app.model.Tag;
import app.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    TagRepository repository;
    TagMapper mapper;

    public TagService(TagRepository repository, TagMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TagDto> getAll() {
        return repository.getAll().stream().map(a -> mapper.toDto(a)).collect(Collectors.toList());
    }

    public TagDto get(Integer id) {
        return mapper.toDto(repository.get(id));
    }

    public void create(TagDto tag) {
        repository.create(tag);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
