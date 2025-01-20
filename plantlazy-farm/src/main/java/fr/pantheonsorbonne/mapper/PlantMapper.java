package fr.pantheonsorbonne.mapper;

import fr.pantheonsorbonne.dto.LogMessage;
import fr.pantheonsorbonne.dto.LogType;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.PlantEntity;

public class PlantMapper {

    public static LogMessage toPlantDiedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessage(LogType.PLANT_DEAD, plantDTO);
    }
    public static LogMessage toPlantCreatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessage(LogType.PLANT_CREATED, plantDTO);
    }
    public static LogMessage toPlantGrownLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessage(LogType.PLANT_GROWN, plantDTO);
    }
    public static LogMessage toPlantUpdatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessage(LogType.PLANT_UPDATE, plantDTO);
    }

    public static PlantDTO toPlantDTO(PlantEntity plant) {
        return new PlantDTO(
                plant.getId(),
                plant.getType(),
                plant.getWater(),
                plant.getSun(),
                plant.getSoil(),
                plant.isDead(),
                plant.getTimeOfDeath(),
                plant.getCauseOfDeath()
        );
    }

}
