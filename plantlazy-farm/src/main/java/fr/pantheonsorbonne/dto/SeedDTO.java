package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.seed.Seed;
import fr.pantheonsorbonne.entity.seed.SeedFactory;
import fr.pantheonsorbonne.entity.seed.SeedQuality;

import java.io.Serial;
import java.io.Serializable;

public record SeedDTO (PlantType type, SeedQuality quality) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Seed toSeed() {
        return SeedFactory.getSeed(this.type, this.quality);
    }

}
