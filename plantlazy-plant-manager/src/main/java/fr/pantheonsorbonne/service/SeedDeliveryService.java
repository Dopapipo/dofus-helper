
package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.model.GenericSeed;
import org.apache.camel.ProducerTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SeedDeliveryService {

    @Inject
    private ProducerTemplate producerTemplate;

    public void sendSeeds(String farmId, String seedType, Integer quantity) {
        GenericSeed.PlantType plantType = GenericSeed.PlantType.valueOf(seedType.toUpperCase());
        GenericSeed.SeedQuality quality = determineSeedQuality(quantity);

        GenericSeed seed = new GenericSeed(plantType, quality);

        try {
            producerTemplate.sendBody("sjms2:plantlazy.seeds", seed);
            System.out.println("Seeds delivery request sent: " + seed);
        } catch (Exception e) {
            System.err.println("Error sending seed delivery request: " + e.getMessage());
        }
    }

    private GenericSeed.SeedQuality determineSeedQuality(Integer quantity) {
        if (quantity <= 10) {
            return GenericSeed.SeedQuality.LOW;
        } else if (quantity <= 50) {
            return GenericSeed.SeedQuality.MEDIUM;
        } else {
            return GenericSeed.SeedQuality.HIGH;
        }
    }
}
