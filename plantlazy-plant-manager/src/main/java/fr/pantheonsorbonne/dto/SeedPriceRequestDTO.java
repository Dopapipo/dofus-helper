
package fr.pantheonsorbonne.dto;

public class SeedPriceRequestDTO {
    private String seedType;

    public SeedPriceRequestDTO(String seedType) {
        this.seedType = seedType;
    }

    public String getSeedType() {
        return seedType;
    }

    public void setSeedType(String seedType) {
        this.seedType = seedType;
    }
}
