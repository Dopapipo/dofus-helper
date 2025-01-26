package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class WaterStat extends GenericPlantStat implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public WaterStat(int value) {
        super(value, 70, 1, StatType.WATER);
    }

    public WaterStat() {
        super();
    }

}
