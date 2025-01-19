package fr.pantheonsorbonne.dto;

public class DeadPlantDTO {
    private int id; // ID de la plante
    private String name; // Nom de la plante

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeadPlantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
