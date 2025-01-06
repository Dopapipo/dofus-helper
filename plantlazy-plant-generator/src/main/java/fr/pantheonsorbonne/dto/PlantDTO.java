package fr.pantheonsorbonne.dto;


public class PlantDTO {
    private Double height;
    private PlantStats stats;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public PlantStats getStats() {
        return stats;
    }

    public void setStats(PlantStats stats) {
        this.stats = stats;
    }

    public PlantDTO(Double height, PlantStats stats) {
        this.height = height;
        this.stats = stats;
    }
}
