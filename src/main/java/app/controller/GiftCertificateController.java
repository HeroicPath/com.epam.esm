package app.controller;


import app.dto.GiftCertificateDto;
import app.service.GiftCertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gift_certificates")
public class GiftCertificateController {

    private final GiftCertificateService service;

    public GiftCertificateController(GiftCertificateService service) {
        this.service = service;
    }

    @GetMapping
    public List<GiftCertificateDto> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GiftCertificateDto get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public void create(GiftCertificateDto giftCertificate) {
        service.create(giftCertificate);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, GiftCertificateDto giftCertificate) {
        service.update(giftCertificate, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
