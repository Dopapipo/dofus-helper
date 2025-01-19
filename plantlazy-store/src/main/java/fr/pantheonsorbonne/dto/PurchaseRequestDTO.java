package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

// demande d'achat de graines
public record PurchaseRequestDTO(PlantType seedType, int quantity, double totalAmount) {
}
