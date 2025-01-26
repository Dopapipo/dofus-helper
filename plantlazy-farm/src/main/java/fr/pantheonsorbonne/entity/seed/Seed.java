package fr.pantheonsorbonne.entity.seed;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.PlantEntity;

// Represente une graine
public interface Seed {
    PlantType getType();
    FullPlantStats getInitialStats();
    PlantEntity grow();
}
