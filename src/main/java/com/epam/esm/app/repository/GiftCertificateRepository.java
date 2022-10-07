package com.epam.esm.app.repository;

import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.model.GiftCertificate;
import com.epam.esm.app.model.Tag;

import java.util.List;

/**
 * An interface that provides db connectivity for crud operations with GiftCertificate entities
 */
public interface GiftCertificateRepository {

    /**
     * retrieves the GiftCertificate from db by id
     * @param id id of the requested entity
     * @return requested entity
     */
    GiftCertificate getById(Integer id);

    /**
     * retrieves the GiftCertificate from db by parameter
     * @param parameter String parameter of the requested entity
     * @param parameterType type of the given parameter (name or description)
     * @return requested entity
     */
    GiftCertificate getByStringParameter(String parameter, String parameterType);

    /**
     * retrieves the list of GiftCertificateDtos from db by tag name
     * @param tagName a name of the Tag that is connected to the requested entities
     * @return list of requested entities
     */
    List<GiftCertificate> getByTagName(String tagName);

    /**
     * creates new GiftCertificate in db with the given parameters and optionally connects new Tag to it (if present)
     * @param giftCertificate new GiftCertificate data
     */
    void create(GiftCertificateDto giftCertificate);

    /**
     * updates the GiftCertificate in db by id with the given parameters
     * @param giftCertificateDto  new data for the GiftCertificate to be updated with
     * @param id id of the to-be-updated entity
     * @param tag new Tag that needs to be connected with the updated GiftCertificate (or null value if no tag is provided by service)
     */
    void update(GiftCertificateDto giftCertificateDto, Integer id, Tag tag);

    /**
     * deletes the specified GiftCertificate from db by id
     * @param id id of the GiftCertificate that needs to be deleted
     */
    void deleteById(Integer id);
}
