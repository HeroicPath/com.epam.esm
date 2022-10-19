package com.epam.esm.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holder {

    @JsonProperty("certificate")
    private GiftCertificateDto certificate;

    @JsonProperty("tag")
    private TagDto tag;

    public Holder(GiftCertificateDto certificate, TagDto tag) {
        this.certificate = certificate;
        this.tag = tag;
    }

    public Holder(GiftCertificateDto certificate) {
        this.certificate = certificate;
    }

    public Holder() {
    }

    public GiftCertificateDto getCertificate() {
        return certificate;
    }

    public void setCertificate(GiftCertificateDto certificate) {
        this.certificate = certificate;
    }

    public TagDto getTag() {
        return tag;
    }

    public void setTag(TagDto tag) {
        this.tag = tag;
    }
}
