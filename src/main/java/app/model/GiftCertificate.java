package app.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class GiftCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @ManyToMany
    @JoinTable(
            name = "tags",
            joinColumns = @JoinColumn(name = "name")
    )
    String description;

    Double price;

    Integer duration;

    LocalDateTime create_date;
    LocalDateTime last_update_date;

    List<Tag> tagList;

    public GiftCertificate(Integer id, String name, String description, Double price, Integer duration, LocalDateTime create_date, LocalDateTime last_update_date, List<Tag> tagList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.create_date = create_date;
        this.last_update_date = last_update_date;
        this.tagList = tagList;
    }

    public GiftCertificate() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public LocalDateTime getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(LocalDateTime last_update_date) {
        this.last_update_date = last_update_date;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
