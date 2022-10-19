package com.epam.esm.app.service;

import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.dto.TagDto;

import java.util.List;

/**
 * A service for CRD operations with GiftCertificate
 */
public interface GiftCertificateService {

    /**
     * returns the GiftCertificateDto by id
     * @param id id of the requested entity
     * @return DTO representation of requested entity
     */
    GiftCertificateDto getById(Integer id);

    /**
     * returns the GiftCertificateDto by name/description
     * @param parameter String parameter of the requested entity
     * @param parameterType type of the given parameter (name or description)
     * @return DTO representation of requested entity
     */
    GiftCertificateDto getByStringParameter(String parameter, String parameterType);

    /**
     * returns the list of GiftCertificateDtos by tag name
     * @param tagName a name of the Tag that is connected to the requested entities
     * @return DTO representations of requested entities
     */
    List<GiftCertificateDto> getByTagName(String tagName);

    /**
     * creates new GiftCertificate with the given parameters and optionally creates new Tag connected to it
     * @param giftCertificateDto new GiftCertificate data
     * @param tagDto new Tag data that is going to be connected to newly created GiftCertificate
     */
    void create(GiftCertificateDto giftCertificateDto, TagDto tagDto);

    /**
     * updates the GiftCertificate by id with the given parameters
     * @param giftCertificateDto new GiftCertificate data and optionally creates new Tag connected to it
     * @param id id of the to-be-updated entity
     * @param tagDto new Tag data that is going to be connected to updated GiftCertificate
     */
    void update(GiftCertificateDto giftCertificateDto, Integer id, TagDto tagDto);

    /**
     * deletes the specified GiftCertificate by id
     * @param id id of the GiftCertificate that needs to be deleted
     */
    void deleteById(Integer id);
}
