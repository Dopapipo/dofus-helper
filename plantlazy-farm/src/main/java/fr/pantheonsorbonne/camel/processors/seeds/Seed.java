package fr.pantheonsorbonne.camel.processors.seeds;

import fr.pantheonsorbonne.camel.processors.plant.Plant;
import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.PlantEntity;

public interface Seed {
    PlantType getType();
    FullPlantStats getInitialStats();
    PlantEntity grow();
    SeedQuality getQuality();
}
