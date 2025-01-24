package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.UUID;

public record PlantInShopLogDTO(UUID id, PlantType type, double price, LogType logType) {

}
