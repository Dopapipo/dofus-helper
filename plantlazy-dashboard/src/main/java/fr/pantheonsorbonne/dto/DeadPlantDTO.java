package fr.pantheonsorbonne.dto;

public class DeadPlantDTO {
    private String id;
    private String name; // Nom de la plante

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
