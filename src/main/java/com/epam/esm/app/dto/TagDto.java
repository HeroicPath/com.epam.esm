package com.epam.esm.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TagDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("list")
    private List<GiftCertificateDto> giftCertificateList;

    public TagDto() {
    }

    public TagDto(String name, List<GiftCertificateDto> giftCertificateList) {
        this.name = name;
        this.giftCertificateList = giftCertificateList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GiftCertificateDto> getGiftCertificateList() {
        return giftCertificateList;
    }

    public void setGiftCertificateList(List<GiftCertificateDto> giftCertificateList) {
        this.giftCertificateList = giftCertificateList;
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "name='" + name + '\'' +
                ", giftCertificateList=" + giftCertificateList +
                '}';
    }
}
