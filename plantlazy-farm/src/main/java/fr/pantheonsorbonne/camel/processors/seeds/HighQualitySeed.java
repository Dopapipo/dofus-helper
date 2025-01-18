package fr.pantheonsorbonne.camel.processors.seeds;

import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.camel.processors.plant.stat.SoilStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.SunStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.WaterStat;

public class HighQualitySeed extends GenericSeed {
    public HighQualitySeed(PlantType type) {
        super(type, SeedQuality.HIGH);
    }
    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(80), new SoilStat(80), new SunStat(80));
    }
}
