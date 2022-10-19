package com.epam.esm.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class TagDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("list")
    private List<GiftCertificateDto> giftCertificateList;
}
