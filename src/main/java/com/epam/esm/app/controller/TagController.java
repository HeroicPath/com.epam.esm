package com.epam.esm.app.controller;

import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.service.TagService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  Controller for the Tag entity
 * </p>
 */
@RestController
@RequestMapping(value = "/tags", consumes = "application/json")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * <p>
     *  Returns the tag by its id
     *  </p>
     * @param id The id of the Tag that needs to be received
     * @return The representation of the requested Tag
     */
    @GetMapping("/{id}")
    public TagDto getById(@PathVariable int id) {
        return tagService.getById(id);
    }

    /**
     *  <p>
     *   Creates the tag using given input
     *  </p>
     * @param tag The tag data that needs to be stored
     */
    @PostMapping
    public void create(@RequestBody TagDto tag) {
        tagService.create(tag);
    }

    /**
     *  <p>
     *  Deletes the tag by its id
     *  </p>
     * @param id The id of the Tag that needs to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        tagService.deleteById(id);
    }
}

