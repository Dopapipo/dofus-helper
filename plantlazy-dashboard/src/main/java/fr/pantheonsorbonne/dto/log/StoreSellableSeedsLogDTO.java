package fr.pantheonsorbonne.dto.log;

import java.util.List;

public class StoreSellableSeedsLogDTO extends LogDTO {

    private String timestamp;
    private List<Seed> seeds;

    public StoreSellableSeedsLogDTO() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    public static class Seed {
        private String seedId;
        private String species;
        private int price;
        private int quantity;

        public Seed() {
        }

        public String getSeedId() {
            return seedId;
        }

        public void setSeedId(String seedId) {
            this.seedId = seedId;
        }

        public String getSpecies() {
            return species;
        }

        public void setSpecies(String species) {
            this.species = species;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
