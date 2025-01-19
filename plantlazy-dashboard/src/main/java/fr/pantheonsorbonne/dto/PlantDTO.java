package fr.pantheonsorbonne.dto;

public class PlantDTO {
    private String id; // Identifiant unique de la plante
    private String type; // Type de plante (Tomato, Carrot, etc.)
    private int waterStat; // Statistique d'eau
    private int sunStat; // Statistique de lumière
    private int soilStat; // Statistique de fertilité du sol

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWaterStat() {
        return waterStat;
    }

    public void setWaterStat(int waterStat) {
        this.waterStat = waterStat;
    }

    public int getSunStat() {
        return sunStat;
    }

    public void setSunStat(int sunStat) {
        this.sunStat = sunStat;
    }

    public int getSoilStat() {
        return soilStat;
    }

    public void setSoilStat(int soilStat) {
        this.soilStat = soilStat;
    }

    @Override
    public String toString() {
        return "PlantDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", waterStat=" + waterStat +
                ", sunStat=" + sunStat +
                ", soilStat=" + soilStat +
                '}';
    }
}
