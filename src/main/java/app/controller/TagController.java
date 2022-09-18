package app.controller;

import app.dto.TagDto;
import app.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public TagDto getById(@PathVariable int id) {
        return tagService.get(id);
    }

    @GetMapping("/name/{name}")
    public TagDto getByName(@PathVariable String name) {
        return tagService.getByName(name);
    }

    @PostMapping
    public void create(@RequestBody TagDto tag) {
        tagService.create(tag);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        tagService.delete(id);
    }
}
