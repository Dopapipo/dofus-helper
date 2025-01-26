package fr.pantheonsorbonne.dto.log;

import java.util.UUID;

public class StoreSellablePlantLogDTO extends LogDTO{

    private UUID id;
    private String plantType;
    private int price;

    public StoreSellablePlantLogDTO() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }


    @Override
    public String toString() {
        return "StoreSellablePlantLogDTO{" +
                "id='" + id + '\'' +
                ", plantType='" + plantType + '\'' +
                ", price=" + price +
                '}';
    }
}
