package fr.pantheonsorbonne.camel.processors.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class WaterStat extends GenericPlantStat {
    public WaterStat(int value) {
        super(value,60,10);
    }

    public WaterStat() {
        super();
    }
}
