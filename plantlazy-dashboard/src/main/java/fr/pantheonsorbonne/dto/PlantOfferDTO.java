package fr.pantheonsorbonne.dto;

public class PlantOfferDTO {
    private String plantType; // Type de plante (Tomate, Courgette, etc.)
    private double sellingPrice; // Prix de vente
    private int quantity; // Quantité disponible

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PlantOfferDTO{" +
                "plantType='" + plantType + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", quantity=" + quantity +
                '}';
    }
}
