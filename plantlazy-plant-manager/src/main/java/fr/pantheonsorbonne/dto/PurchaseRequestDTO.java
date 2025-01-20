package fr.pantheonsorbonne.dto;

// demande d'achat de graines
public record PurchaseRequestDTO(PlantType seedType, int quantity) {
}

