package fr.pantheonsorbonne.entity.seed;


import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;

public class LowQualitySeed extends GenericSeed {
    public LowQualitySeed(PlantType type) {
        super(type, SeedQuality.LOW);
    }

    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(60), new SoilStat(60), new SunStat(60));
    }
}