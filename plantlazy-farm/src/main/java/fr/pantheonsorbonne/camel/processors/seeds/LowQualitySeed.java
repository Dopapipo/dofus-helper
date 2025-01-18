package fr.pantheonsorbonne.camel.processors.seeds;


import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.camel.processors.plant.stat.SoilStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.SunStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.WaterStat;

public class LowQualitySeed extends GenericSeed {
    public LowQualitySeed(PlantType type) {
        super(type, SeedQuality.LOW);
    }

    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(60), new SoilStat(60), new SunStat(60));
    }
}
