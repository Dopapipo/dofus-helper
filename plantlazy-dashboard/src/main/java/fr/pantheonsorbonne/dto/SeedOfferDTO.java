package fr.pantheonsorbonne.dto;

public class SeedOfferDTO {
    private String seedType;
    private int quantity;
    private double price;

    public String getSeedType() {
        return seedType;
    }

    public void setSeedType(String seedType) {
        this.seedType = seedType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SeedOfferDTO{" +
                "seedType='" + seedType + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
