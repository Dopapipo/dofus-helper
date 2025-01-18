
package fr.pantheonsorbonne.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeedPriceDTO {
    @JsonProperty("seedType")
    private String seedType;

    @JsonProperty("price")
    private double price;

    public SeedPriceDTO() {}

    public SeedPriceDTO(String seedType, double price) {
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
