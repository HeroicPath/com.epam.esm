package app.controller;

import app.dto.TagDto;
import app.service.TagService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

//    @GetMapping
//    public List<TagDto> index() {
//        return service.getAll();
//    }

    @GetMapping(value = "/single/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagDto> get(@PathVariable int id) {
        return ResponseEntity.ok()
                .contentType(new MediaType("application","json"))
                .body(new TagDto("success"));
    }

    @PostMapping
    public void create(TagDto tag) {
        service.create(tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
