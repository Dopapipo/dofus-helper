package fr.pantheonsorbonne.dto.log;

public class StoreSoldPlantLogDTO extends LogDTO {

    private String plantId;
    private int price;

    public StoreSoldPlantLogDTO() {
    }


    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
