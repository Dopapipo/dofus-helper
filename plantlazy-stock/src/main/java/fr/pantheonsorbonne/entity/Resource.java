package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResourceType type;
    private Double quantity;


    public Resource() {

    }

    public Long getId() {
        return id;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Double getQuantity() {
        return (quantity != null) ? quantity : 0.0;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}