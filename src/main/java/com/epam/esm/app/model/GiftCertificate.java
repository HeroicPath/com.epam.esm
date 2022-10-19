package com.epam.esm.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
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
}
