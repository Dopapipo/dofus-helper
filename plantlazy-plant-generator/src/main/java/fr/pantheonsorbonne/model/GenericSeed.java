
package fr.pantheonsorbonne.plantlazy.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericSeed {

    public enum PlantType {
        CACTUS, FLOWER, TREE
    }

    public enum SeedQuality {
        LOW, MEDIUM, HIGH
    }

    @JsonProperty("type")
    private PlantType type;

    @JsonProperty("quality")
    private SeedQuality quality;

    public GenericSeed(PlantType type, SeedQuality quality) {
        this.type = type;
        this.quality = quality;
    }

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }

    public SeedQuality getQuality() {
        return quality;
    }

    public void setQuality(SeedQuality quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "GenericSeed{" +
                "type=" + type +
                ", quality=" + quality +
                '}';
    }
}

