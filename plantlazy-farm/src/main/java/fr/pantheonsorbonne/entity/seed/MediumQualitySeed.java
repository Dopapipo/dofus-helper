package fr.pantheonsorbonne.entity.seed;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;

public class MediumQualitySeed extends GenericSeed {
    MediumQualitySeed(PlantType type) {
        super(type, SeedQuality.MEDIUM);
    }
    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(70), new SoilStat(70), new SunStat(70));
    }
}
