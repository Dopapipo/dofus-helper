package fr.pantheonsorbonne.entity.seed;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;

public class HighQualitySeed extends GenericSeed {
    public HighQualitySeed(PlantType type) {
        super(type, SeedQuality.HIGH);
    }
    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(80), new SoilStat(80), new SunStat(80));
    }
}