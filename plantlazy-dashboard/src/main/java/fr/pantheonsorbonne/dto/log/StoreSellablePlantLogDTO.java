package fr.pantheonsorbonne.dto.log;

public class StoreSellablePlantLogDTO {

    private String type;
    private String plantId;
    private String name;
    private int price;

    public StoreSellablePlantLogDTO() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
