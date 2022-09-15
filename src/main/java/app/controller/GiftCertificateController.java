package app.controller;


import app.dto.GiftCertificateDto;
import app.model.Holder;
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

    @GetMapping("/name/{name}")
    public GiftCertificateDto getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("/description/{description}")
    public GiftCertificateDto getByDescription(@PathVariable String description) {
        return service.getByDescription(description);
    }

    @PostMapping
    public void create(@RequestBody Holder holder) {
        service.create(holder.getCertificate(),
                        holder.getTag());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Holder holder) {
        service.update(holder.getCertificate(),
                        id,
                        holder.getTag());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
