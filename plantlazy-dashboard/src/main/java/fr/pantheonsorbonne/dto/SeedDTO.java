package fr.pantheonsorbonne.dto;

public class SeedDTO {
    private String id;        // Identifiant unique de la graine
    private String type;      // Type de plante (ex: TREE, FLOWER, etc.)
    private String quality;   // Qualité de la graine (ex: HIGH, MEDIUM, LOW)
    private int price;     // Prix de la graine

    public SeedDTO() {
    }

    public SeedDTO(String id, String type, String quality, int price) {
        this.id = id;
        this.type = type;
        this.quality = quality;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SeedDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", quality='" + quality + '\'' +
                ", price=" + price +
                '}';
    }
}
