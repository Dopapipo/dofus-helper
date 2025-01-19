package fr.pantheonsorbonne.dto.update;

import fr.pantheonsorbonne.dto.PlantDTO;

import java.util.List;

public class PlantUpdateDTO {
    private String type; // PLANT_UPDATE
    private List<PlantDTO> plants; // Liste des plantes mises à jour

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PlantDTO> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantDTO> plants) {
        this.plants = plants;
    }

    @Override
    public String toString() {
        return "PlantUpdateDTO{" +
                "type='" + type + '\'' +
                ", plants=" + plants +
                '}';
    }
}
