package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import fr.pantheonsorbonne.service.PlantService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.camel.CamelContext;
import org.apache.camel.component.mock.MockEndpoint;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Disabled;
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

    private PlantEntity createTestPlant() {
        return new PlantEntity(PlantType.FLOWER, new WaterStat(100), new SunStat(100), new SoilStat(100));
    }

    @Test
    @Transactional
    void testHourlyTickProcessesPlantLifecycle() {
        PlantEntity expectedPlant = createTestPlant();
        expectedPlant.grow();
        PlantEntity persistedPlant = plantRepository.save(createTestPlant());
        em.flush();

        plantService.processHouryPlantLifecycle();
        camelContext.createProducerTemplate().sendBody(tickEndpoint, new TickMessage(TickType.HOURLY, System.currentTimeMillis()));

        PlantEntity updatedPlant = plantRepository.findById(persistedPlant.getId());
        assertEquals(expectedPlant.getStats(), updatedPlant.getStats(), "Plant stats should be updated correctly.");
    }

}




