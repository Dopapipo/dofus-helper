package fr.pantheonsorbonne.mapper;

import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.entity.plant.stat.StatType;

public class TypeMapper {
    public static ResourceType toResourceType(StatType statType) {
        return switch (statType) {
            case SUN -> ResourceType.ENERGY;
            case WATER -> ResourceType.WATER;
            case SOIL -> ResourceType.FERTILIZER;
        };
    }

}
