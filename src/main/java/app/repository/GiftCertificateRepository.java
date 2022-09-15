package app.repository;

import app.dto.GiftCertificateDto;
import app.model.GiftCertificate;
import app.model.Tag;

import java.util.List;

public interface GiftCertificateRepository {

    List<GiftCertificate> getAll();

    GiftCertificate get(Integer uuid);

    GiftCertificate getByDescription(String name);

    void create(GiftCertificateDto giftCertificate);

    void update(GiftCertificateDto giftCertificate, Integer uuid, Tag tag);

    void delete(Integer uuid);

    GiftCertificate getByName(String name);
}
