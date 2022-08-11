package app.dto;

public class GiftCertificateDto {

    private Integer id;
    private String Name;
    private String Description;
    private Double price;
    private Integer duration;

    public GiftCertificateDto(Integer id, String name, String description, Double price, Integer duration) {
        this.id = id;
        Name = name;
        Description = description;
        this.price = price;
        this.duration = duration;
    }

    public GiftCertificateDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
