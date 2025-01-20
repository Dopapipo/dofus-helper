
package fr.pantheonsorbonne.dto;

public class SeedPriceResponseDTO {
    private String seedType;
    private double price;

    public SeedPriceResponseDTO(String seedType, double price) {
        this.seedType = seedType;
        this.price = price;
    }

    public String getSeedType() {
        return seedType;
    }

    public void setSeedType(String seedType) {
        this.seedType = seedType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
