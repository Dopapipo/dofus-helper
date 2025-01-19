package fr.pantheonsorbonne.dto;

public class PlantOfferDTO {
    private String plantType;
    private double sellingPrice;
    private int quantity;

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
