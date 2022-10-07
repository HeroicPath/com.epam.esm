package com.epam.esm.app.controller;


import com.epam.esm.app.dto.GiftCertificateDto;
import com.epam.esm.app.model.Holder;
import com.epam.esm.app.service.GiftCertificateService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  Controller for the Gift Certificate entity
 * </p>
 */
@RestController
@RequestMapping(value = "gift_certificates", consumes = "application/json")
public class GiftCertificateController {

    private final GiftCertificateService service;

    public GiftCertificateController(GiftCertificateService service) {
        this.service = service;
    }

    /**
     * <p>
     *  Returns the Gift Certificate by its id
     *  </p>
     * @param id The id of the Gift Certificate that needs to be received
     * @return The representation of the requested Gift Certificate
     */
    @GetMapping("/{id}")
    public GiftCertificateDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    /**
     * <p>
     *  Returns the Gift Certificate by its id
     *  </p>
     * @param name The name of the Gift Certificate that needs to be received
     * @return The representation of the requested Gift Certificate
     */
    @GetMapping("/name/{name}")
    public GiftCertificateDto getByName(@PathVariable String name) {
        return service.getByStringParameter(name, "name");
    }

    /**
     * <p>
     *  Returns the Gift Certificate by its description
     *  </p>
     * @param description The description of the Gift Certificate that needs to be received
     * @return The representation of the requested Gift Certificate
     */
    @GetMapping("/description/{description}")
    public GiftCertificateDto getByDescription(@PathVariable String description) {
        return service.getByStringParameter(description, "description");
    }

    /**
     * <p>
     *  Returns the Gift Certificate by the name of the tag that is connected to it
     *  </p>
     * @param tagName The name of the tag that is connected to the Gift Certificate that needs to be received
     * @return The representation of the requested Gift Certificate
     */
    @GetMapping("/tagname/{tagName}")
    public List<GiftCertificateDto> getByTagName(@PathVariable String tagName) {
        return service.getByTagName(tagName);
    }

    /**
     *  <p>
     *   Creates the Gift Certificate using given input
     *  </p>
     * @param holder The Gift Certificate data that needs to be stored and the optional Tag data that needs to be stored and connected to the given Gift Certificate
     */
    @PostMapping
    public void create(@RequestBody Holder holder) {
        if (ObjectUtils.isEmpty(holder)){
//            throw new LocalException(HttpStatus.BAD_REQUEST, "You need to specify the gift certificate data");
        }
        // TODO: 07.10.2022 fix exception throwing
        service.create(holder.getCertificate(),
                        holder.getTag());
    }

    /**
     *  <p>
     *   Updates the Gift Certificate using given input and the id of the existing Gift Certificate
     *  </p>
     * @param holder The Gift Certificate data that needs to be stored and the optional Tag data that needs to be stored and connected to the given Gift Certificate
     * @param id The id of the Gift Certificate that needs to be updated
     */
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Holder holder) {
        service.update(holder.getCertificate(),
                        id,
                        holder.getTag());
    }

    /**
     *  <p>
     *  Deletes the Gift Certificate by its id
     *  </p>
     * @param id The id of the Gift Certificate that needs to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
