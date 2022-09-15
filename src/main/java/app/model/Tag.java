package app.model;

import app.dto.GiftCertificateDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {

    @Id
    Integer id;

    @ManyToMany
    @JoinTable(
            name = "gift_certificates",
            joinColumns = @JoinColumn(name = "description")
    )
    String name;

    List<GiftCertificateDto> giftCertificates;

    public Tag(Integer id, String name, List<GiftCertificateDto> giftCertificates) {
        this.id = id;
        this.name = name;
        this.giftCertificates = giftCertificates;
    }

    public Tag() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GiftCertificateDto> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(List<GiftCertificateDto> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }
}
