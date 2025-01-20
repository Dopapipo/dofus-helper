package fr.pantheonsorbonne.dto.update;

import fr.pantheonsorbonne.dto.PlantOfferDTO;

import java.util.List;

public class PlantSaleUpdateDTO {
    private String type; // STORE_PLANTS_DAILY_OFFER
    private List<PlantOfferDTO> plants; // Liste des plantes en vente

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PlantOfferDTO> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantOfferDTO> plants) {
        this.plants = plants;
    }

    @Override
    public String toString() {
        return "StoreSellablePlants{" +
                "type='" + type + '\'' +
                ", plants=" + plants +
                '}';
    }
}
