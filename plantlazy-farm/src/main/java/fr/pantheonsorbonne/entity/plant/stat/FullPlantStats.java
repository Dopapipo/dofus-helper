package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class FullPlantStats {
    private WaterStat waterStat;
    private SoilStat soilStat;
    private SunStat sunStat;

    public FullPlantStats(WaterStat waterStat, SoilStat soilStat, SunStat sunStat) {
        this.waterStat = waterStat;
        this.soilStat = soilStat;
        this.sunStat = sunStat;
    }

    public FullPlantStats() {

    }

    public WaterStat getWaterStat() {
        return waterStat;
    }

    public SoilStat getSoilStat() {
        return soilStat;
    }

    public SunStat getSunStat() {
        return sunStat;
    }

    public boolean isDead() {
        return (soilStat.isDead() || waterStat.isDead() || sunStat.isDead()) || (!soilStat.isHealthy() && !waterStat.isHealthy() && !sunStat.isHealthy());
    }
}
