package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.camel.CamelContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class SeedIntegrationTest {
    @Inject
    @ConfigProperty(name = "plant.seed.endpoint")
    String seedEndpoint;

    @Inject
    PlantRepository plantRepository;

    @Inject
    EntityManager em;

    @Inject
    CamelContext camelContext;
    SeedDTO sentSeedDTO;
    PlantEntity expectedPlantEntity;

    @BeforeEach
    void setUp() {

        sentSeedDTO = new SeedDTO(PlantType.FLOWER, SeedQuality.MEDIUM);
        expectedPlantEntity = new PlantEntity(PlantType.FLOWER, sentSeedDTO.toSeed().getInitialStats());


    }

    @Test
    @Transactional
    @Disabled
    void testSeedConsumerProcess() {
        // Arrange: Simulate sending a seed to the consumer
        plantRepository.save(expectedPlantEntity);
        em.flush();

        // Act: Simulate seed consumption (Camel route)
        camelContext.createProducerTemplate().sendBody(seedEndpoint, sentSeedDTO);

        // Assert: Verify that the plant has been persisted and processed correctly
        PlantEntity persistedPlant = plantRepository.findById(expectedPlantEntity.getId());
        assertEquals(expectedPlantEntity.getStats(), persistedPlant.getStats(), "Plant stats should match expected values after seed consumption.");
    }
}
