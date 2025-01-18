package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.seed.Seed;
import fr.pantheonsorbonne.entity.seed.SeedFactory;
import fr.pantheonsorbonne.entity.seed.SeedQuality;

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
