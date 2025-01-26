package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.LogType;

import java.util.UUID;

public record LogMessagePlantDiedOrSold (LogType type, UUID id){
}
