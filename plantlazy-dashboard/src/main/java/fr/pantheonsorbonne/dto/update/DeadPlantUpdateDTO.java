package fr.pantheonsorbonne.dto.update;

import fr.pantheonsorbonne.dto.DeadPlantDTO;

import java.util.List;

public class DeadPlantUpdateDTO {
    private String type; // DEAD_PLANT_UPDATE
    private List<DeadPlantDTO> plants; // Liste des plantes mortes

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DeadPlantDTO> getPlants() {
        return plants;
    }

    public void setPlants(List<DeadPlantDTO> plants) {
        this.plants = plants;
    }

    @Override
    public String toString() {
        return "DeadPlantUpdateDTO{" +
                "type='" + type + '\'' +
                ", plants=" + plants +
                '}';
    }
}
