package com.epam.esm.app.repository;


import com.epam.esm.app.dto.TagDto;
import com.epam.esm.app.model.Tag;

/**
 * An interface that provides db connectivity for crud operations with Tag entities
 */
public interface TagRepository {

    /**
     * retrieves the Tag from db by id
     * @param id id of the requested entity
     * @return the requested entity
     */
    Tag getById(Integer id);

    /**
     * retrieves the Tag from db by name
     * @param name name of the requested entity
     * @return the requested entity
     */
    Tag getByName(String name);

    /**
     * creates a new Tag in db with the given parameters
     * @param tagDto new Tag data
     */
    void create(TagDto tagDto);

    /**
     * deletes the specified Tag from db by id
     * @param id id of the Tag that needs to be deleted
     */
    void deleteById(Integer id);

    /**
     * returns true if Tag with specified name exists in the db
     * @param name name of the Tag that needs to be searched
     * @return boolean value
     */
    boolean isPresent(String name);
}
