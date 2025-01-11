package fr.pantheonsorbonne.camel.processors.plant.stat;

public class FullPlantStats {
   private WaterStat waterStat;
   private SoilStat soilStat;
   private SunStat sunStat;

    public FullPlantStats(WaterStat waterStat, SoilStat soilStat, SunStat sunStat) {
        this.waterStat = waterStat;
        this.soilStat = soilStat;
        this.sunStat = sunStat;
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
}
