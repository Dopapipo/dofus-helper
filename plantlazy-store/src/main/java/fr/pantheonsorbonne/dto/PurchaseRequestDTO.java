package fr.pantheonsorbonne.dto;

// demande d'achat de graines
public record PurchaseRequestDTO(String seedType, int quantity, double totalAmount) {
}
