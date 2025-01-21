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
    @ConfigProperty(name = "plant.transport.endpoint")
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
        // Arrange
        PlantEntity expectedPlant = createTestPlant();
        expectedPlant.grow();
        PlantEntity persistedPlant = plantRepository.save(createTestPlant());
        em.flush();

        // Act - Simulate expected changes
        plantService.processPlantLifecycle(); // Calls triggerPlantGrowth internally
        camelContext.createProducerTemplate().sendBody(tickEndpoint, new TickMessage(TickType.HOURLY, System.currentTimeMillis()));

        // Assert
        PlantEntity updatedPlant = plantRepository.findById(persistedPlant.getId());
        assertEquals(expectedPlant.getStats(), updatedPlant.getStats(), "Plant stats should be updated correctly.");
    }

    @Test
    @Transactional
    @Disabled
    void testDailyTickSendsDeadPlantMessage() throws Exception {
        // Arrange
        PlantEntity deadPlant = createTestPlant();
        deadPlant.setDead(true);
        plantRepository.save(deadPlant);
        em.flush();

        // Mocking the transport and log endpoints to capture the messages sent
        MockEndpoint transportMock = camelContext.getEndpoint("mock:" + transportEndpoint, MockEndpoint.class);
        MockEndpoint logMock = camelContext.getEndpoint("mock:" + logEndpoint, MockEndpoint.class);

        // Set expectations for the MockEndpoints
        transportMock.expectedMessageCount(1);
        logMock.expectedMessageCount(1);

        // Act
        // Send the daily tick message
        camelContext.createProducerTemplate().sendBody(tickEndpoint, new TickMessage(TickType.DAILY, System.currentTimeMillis()));

        // Assert
        // Verify that the correct number of messages were received by each mock endpoint
        transportMock.assertIsSatisfied();
        logMock.assertIsSatisfied();
    }
}




