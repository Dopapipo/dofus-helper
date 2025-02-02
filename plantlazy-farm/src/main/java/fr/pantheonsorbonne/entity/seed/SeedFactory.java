package fr.pantheonsorbonne.entity.seed;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

public class SeedFactory {
    public static Seed getSeed(PlantType type, SeedQuality quality) {
        return switch (quality) {
            case MEDIUM -> new MediumQualitySeed(type);
            case HIGH -> new HighQualitySeed(type);
            default -> new LowQualitySeed(type);
        };
    }
}
