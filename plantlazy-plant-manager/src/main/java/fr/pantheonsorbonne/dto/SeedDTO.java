
package fr.pantheonsorbonne.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeedDTO {
    @JsonProperty("type")
    private String type;

    @JsonProperty("quantity")
    private int quantity;

    public SeedDTO() {}

    public SeedDTO(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
