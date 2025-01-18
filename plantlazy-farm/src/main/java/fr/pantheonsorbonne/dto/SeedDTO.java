package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.seeds.Seed;
import fr.pantheonsorbonne.camel.processors.seeds.SeedFactory;
import fr.pantheonsorbonne.camel.processors.seeds.SeedQuality;
import fr.pantheonsorbonne.entity.SeedEntity;

public class SeedDTO {
    private PlantType type;
    private SeedQuality quality;
    public SeedDTO(PlantType type, SeedQuality quality) {
        this.type = type;
        this.quality = quality;
    }
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

    public Seed toSeed() {
        return SeedFactory.getSeed(this.type, this.quality);
    }

}
