package fr.pantheonsorbonne.dto.log;

import fr.pantheonsorbonne.dto.SeedDTO;

import java.util.List;

public class StoreSellableSeedsLogDTO extends LogDTO {

    private String timestamp;
    private List<SeedDTO> seeds;

    public StoreSellableSeedsLogDTO() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<SeedDTO> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<SeedDTO> seeds) {
        this.seeds = seeds;
    }
}
