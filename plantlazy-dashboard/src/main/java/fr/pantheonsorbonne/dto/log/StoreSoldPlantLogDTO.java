package fr.pantheonsorbonne.dto.log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreSoldPlantLogDTO extends LogDTO {
    private UUID id;
    private String plantType;
    private int price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StoreSoldPlantLogDTO{" +
                "plantId=" + id +
                ", plantType='" + plantType + '\'' +
                ", price=" + price +
                '}';
    }
}
