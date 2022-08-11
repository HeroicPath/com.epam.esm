package app.repository;

import app.dto.GiftCertificateDto;
import app.model.GiftCertificate;

import java.util.List;

public interface GiftCertificateRepository {

    List<GiftCertificate> getAll();

    GiftCertificate get(Integer uuid);

    void create(GiftCertificateDto giftCertificate);

    void update(GiftCertificateDto giftCertificate, Integer uuid);

    void delete(Integer uuid);
}
