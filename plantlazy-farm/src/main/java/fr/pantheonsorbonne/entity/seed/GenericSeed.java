package fr.pantheonsorbonne.entity.seed;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import fr.pantheonsorbonne.entity.PlantEntity;

public class GenericSeed implements Seed{
    private PlantType type;
    private SeedQuality quality;

    public GenericSeed(PlantType type, SeedQuality quality) {
        this.type = type;
        this.quality = quality;
    }


    public SeedQuality getQuality() {
        return this.quality;
    }

    @Override
    public PlantType getType() {
        return this.type;
    }

    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(100), new SoilStat(100), new SunStat(100));
    }

    @Override
    public PlantEntity grow() {
        return new PlantEntity(this.type, this.getInitialStats());
    }
}
