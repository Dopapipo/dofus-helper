package fr.pantheonsorbonne.camel.processors.seeds;

import fr.pantheonsorbonne.camel.processors.plant.Plant;
import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.camel.processors.plant.stat.SoilStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.SunStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.WaterStat;
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
        return new FullPlantStats(new WaterStat(50), new SoilStat(50), new SunStat(50));
    }

    @Override
    public PlantEntity grow() {
        return new PlantEntity(this.type, this.getInitialStats());
    }
}
