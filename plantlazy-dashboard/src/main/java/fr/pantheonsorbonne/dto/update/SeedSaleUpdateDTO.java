package fr.pantheonsorbonne.dto.update;

import fr.pantheonsorbonne.dto.SeedOfferDTO;

import java.util.List;

public class SeedSaleUpdateDTO {
    private String type; // STORE_SELLABLE_PLANTS
    private List<SeedOfferDTO> seeds; // Liste des graines en vente

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SeedOfferDTO> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<SeedOfferDTO> seeds) {
        this.seeds = seeds;
    }

    @Override
    public String toString() {
        return "StoreSellablePlantsDTO{" +
                "type='" + type + '\'' +
                ", seeds=" + seeds +
                '}';
    }
}
