package fr.pantheonsorbonne.entity.seed;

import fr.pantheonsorbonne.entity.enums.PlantType;

public class SeedFactory {
    public static Seed getSeed(PlantType type, SeedQuality quality) {
        return switch (quality) {
            case MEDIUM -> new MediumQualitySeed(type);
            case HIGH -> new HighQualitySeed(type);
            default -> new LowQualitySeed(type);
        };
    }
}
