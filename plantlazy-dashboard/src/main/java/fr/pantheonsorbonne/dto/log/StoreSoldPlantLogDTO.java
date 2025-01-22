package fr.pantheonsorbonne.dto.log;

public class StoreSoldPlantLogDTO extends LogDTO {

    private String type;
    private String plantId;
    private int price;

    public StoreSoldPlantLogDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
