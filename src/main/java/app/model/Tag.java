package app.model;

import javax.persistence.*;

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

    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
