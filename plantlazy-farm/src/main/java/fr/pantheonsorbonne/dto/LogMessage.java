package fr.pantheonsorbonne.dto;

public class LogMessage {
    private LogType type;
    private PlantDTO plantDTO;

    public LogMessage(LogType type, PlantDTO plantDTO) {
        this.type = type;
        this.plantDTO = plantDTO;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public PlantDTO getPlantDTO() {
        return plantDTO;
    }

    public void setPlantDTO(PlantDTO plantDTO) {
        this.plantDTO = plantDTO;
    }
}
