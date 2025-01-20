
package fr.pantheonsorbonne.dto;

public class DeadPlantDTO {
    private String plantType;
    private int quantity;

    public DeadPlantDTO(String plantType, int quantity) {
        this.plantType = plantType;
        this.quantity = quantity;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
