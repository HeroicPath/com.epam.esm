package com.epam.esm.app.dto;

import com.epam.esm.app.model.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GiftCertificateDto {

    @JsonProperty("name")
    private String Name;

    @JsonProperty("description")
    private String Description;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("duration")
    private Integer duration;

    private List<Tag> tags;

    public GiftCertificateDto(String name, String description, Double price, Integer duration, List<Tag> tags) {
        Name = name;
        Description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }

    public GiftCertificateDto() {
    }
}
