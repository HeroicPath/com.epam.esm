package com.epam.esm.app.service;

import com.epam.esm.app.dto.TagDto;

/**
 * A service for CRD operations with Tag
 */
public interface TagService {

    /**
     * returns the TagDto by id
     * @param id id of the requested entity
     * @return DTO representation of requested entity
     */
    TagDto getById(Integer id);

    /**
     * returns the TagDto by name
     * @param name name of the requested entity
     * @return DTO representation of requested entity
     */
    TagDto getByName(String name);
    /**
     * creates new Tag with the given parameters
     * @param tagDto new Tag data
     */
    void create(TagDto tagDto);

    /**
     * deletes the specified Tag by id
     * @param id id of the Tag that needs to be deleted
     */
    void deleteById(Integer id);
}
