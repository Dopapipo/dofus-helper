package fr.pantheonsorbonne.dto;

public class SeedOfferDTO {
    private String seedType; // Type de graine (Tomate, Courgette, etc.)
    private int quantity; // Quantité en vente
    private double price; // Prix par unité

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
