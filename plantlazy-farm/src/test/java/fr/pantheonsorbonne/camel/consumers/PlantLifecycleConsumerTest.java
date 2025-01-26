package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.entity.enums.TickType;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.StatType;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import fr.pantheonsorbonne.service.PlantService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.camel.CamelContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PlantLifecycleConsumerTest {

    @Inject
    PlantService plantService;

    @Inject
    PlantRepository plantRepository;
    @Inject
    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;
    @Inject
    @ConfigProperty(name = "dead.plant.transport.endpoint")
    String transportEndpoint;
    @Inject
    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;

    @Inject
    CamelContext camelContext;

    @Inject
    EntityManager em;

    private PlantEntity createTestPlant(int statLevel) {
        return new PlantEntity(PlantType.FLOWER, new WaterStat(statLevel), new SunStat(statLevel), new SoilStat(statLevel));
    }

    @Test
    @Transactional
    void testHourlyTickProcessesPlantLifecycle() {
        PlantEntity expectedPlant = createTestPlant(100);
        expectedPlant.grow();
        PlantEntity persistedPlant = plantRepository.save(createTestPlant(100));
        em.flush();

        plantService.processHourlyPlantLifecycle();

        PlantEntity updatedPlant = plantRepository.findById(persistedPlant.getId());
        assertEquals(expectedPlant.getStats(), updatedPlant.getStats(), "Plant stats should be updated correctly.");
    }

    @Test
    @Transactional
    void testHourlyTickFeedPlants() {
        SunStat filler = new SunStat();
        int threshold = filler.getThreshold();
        int decayRate = filler.getDecayRate();
        PlantEntity expectedPlant = createTestPlant(threshold + decayRate);
        expectedPlant.feed(StatType.SUN, filler.getOptimalRessourceQuantityToFeed());
        PlantEntity persistedPlant = plantRepository.save(createTestPlant(threshold + decayRate));
        em.flush();

        plantService.processHourlyPlantLifecycle();
        camelContext.createProducerTemplate().sendBody(tickEndpoint, new TickMessage(TickType.HOURLY, System.currentTimeMillis()));

        PlantEntity updatedPlant = plantRepository.findById(persistedPlant.getId());
        assertEquals(expectedPlant.getStats(), updatedPlant.getStats(), "Plant stats should be updated correctly.");
    }

}




