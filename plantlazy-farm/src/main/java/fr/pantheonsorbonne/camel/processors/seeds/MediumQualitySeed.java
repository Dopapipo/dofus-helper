package fr.pantheonsorbonne.camel.processors.seeds;

import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.camel.processors.plant.stat.SoilStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.SunStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.WaterStat;

public class MediumQualitySeed extends GenericSeed {
    MediumQualitySeed(PlantType type) {
        super(type, SeedQuality.MEDIUM);
    }
    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(70), new SoilStat(70), new SunStat(70));
    }
}
