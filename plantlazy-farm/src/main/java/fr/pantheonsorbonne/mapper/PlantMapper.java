package fr.pantheonsorbonne.mapper;

import fr.pantheonsorbonne.dto.*;
import fr.pantheonsorbonne.entity.PlantEntity;

public class PlantMapper {

    public static LogMessagePlantDied toPlantDiedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessagePlantDied(LogType.PLANT_DEAD, plantDTO.getId());
    }

    public static LogMessagePlantSold toPlantSoldLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessagePlantSold(LogType.PLANT_SOLD, plantDTO.getId());
    }
    public static LogMessagePlantCreated toPlantCreatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessagePlantCreated(LogType.PLANT_CREATED, plantDTO.getId(), plantDTO.type(), plantDTO.growthLevel(), plantDTO.water(), plantDTO.sun(), plantDTO.soil());
    }

    public static LogMessageUpdate toPlantUpdatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessageUpdate(LogType.PLANT_UPDATE, plantDTO.getId(), plantDTO.type(), plantDTO.growthLevel(), plantDTO.water(), plantDTO.sun(), plantDTO.soil());
    }

    public static PlantDTO toPlantDTO(PlantEntity plant) {
        return new PlantDTO(plant);
    }
    public static PlantEntity toPlantEntity(PlantDTO plantDTO) {
        return new PlantEntity(plantDTO);
    }

}
