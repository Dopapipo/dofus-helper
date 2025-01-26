package fr.pantheonsorbonne.dto.log;

public class DeadPlantLogDTO extends LogDTO {

    private String plantId;
    private String name;
    private int decompositionLevel;

    public DeadPlantLogDTO() {
    }


    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public int getDecompositionLevel() {
        return decompositionLevel;
    }

    public void setDecompositionLevel(int decompositionLevel) {
        this.decompositionLevel = decompositionLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
