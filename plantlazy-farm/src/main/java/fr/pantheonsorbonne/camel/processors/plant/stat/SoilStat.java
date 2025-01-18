package fr.pantheonsorbonne.camel.processors.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class SoilStat extends GenericPlantStat {
    public SoilStat(int value) {
        super(value,70,5);
    }

    public SoilStat() {
        super();
    }
}
