package fr.pantheonsorbonne.dto;

// vente de plante, à envoyer au microservice Stock
public record PlantSaleDTO(String plantType, double sellingPrice, int quantity) {
}

