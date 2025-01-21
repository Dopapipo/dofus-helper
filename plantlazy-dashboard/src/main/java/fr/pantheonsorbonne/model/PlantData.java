package fr.pantheonsorbonne.model;

public class PlantData {
    private String id;
    private String name;
    private int waterLevel;
    private int energyLevel;
    private int fertilizerLevel;
    private int GrowthLevel;
    private int price;
    private int quantity;
    private int decompositionLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getGrowthLevel() {
        return GrowthLevel;
    }

    public void setGrowthLevel(int growthLevel) {
        GrowthLevel = growthLevel;
    }

    public int getDecompositionLevel() {
        return decompositionLevel;
    }

    public void setDecompositionLevel(int decompositionLevel) {
        this.decompositionLevel = decompositionLevel;
    }

    @Override
    public String toString() {
        return "PlantData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", waterLevel=" + waterLevel +
                ", energyLevel=" + energyLevel +
                ", fertilizerLevel=" + fertilizerLevel +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
