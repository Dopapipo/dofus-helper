package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.seeds.SeedQuality;

public class SeedDTO {
    private PlantType type;
    private SeedQuality quality;

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }

    public SeedQuality getQuality() {
        return quality;
    }

    public void setQuality(SeedQuality quality) {
        this.quality = quality;
    }
}
