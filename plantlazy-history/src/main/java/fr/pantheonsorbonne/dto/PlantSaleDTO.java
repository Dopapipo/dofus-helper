package fr.pantheonsorbonne.dto;

public class PlantSaleDTO {
    private String plantId;
    private String plantName;
    private int updatedPrice;
    private int updatedSalesRate;

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getUpdatedPrice() {
        return updatedPrice;
    }

    public void setUpdatedPrice(int updatedPrice) {
        this.updatedPrice = updatedPrice;
    }

    public int getUpdatedSalesRate() {
        return updatedSalesRate;
    }

    public void setUpdatedSalesRate(int updatedSalesRate) {
        this.updatedSalesRate = updatedSalesRate;
    }
}
