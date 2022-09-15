package app.dto;

import app.model.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import javax.persistence.Column;
import java.util.List;

public class GiftCertificateDto {

    @JsonProperty("name")
    @Column(length = 30)
    private String Name;

    @JsonProperty("description")
    private String Description;

    @JsonProperty("price")
    @Min(0) @NotBlank(message = "Enter the price")
    private Double price;

    @JsonProperty("duration")
    @Min(0) @Max(99)
    private Integer duration;

    private List<Tag> tags;

    public GiftCertificateDto(String name, String description, @Min(0) @NotBlank(message = "Enter the price") Double price, @Min(0) @Max(99) Integer duration, List<Tag> tags) {
        Name = name;
        Description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }

    public GiftCertificateDto() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "GiftCertificateDto{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", tags=" + tags +
                '}';
    }
}
