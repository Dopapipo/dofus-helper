package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import fr.pantheonsorbonne.service.PlantService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(PlantLifecycleConsumer.class)
public class PlantLifecycleConsumerTest {

    @InjectMock
    PlantService plantService;

    @Inject
    PlantRepository plantRepository;

    @Inject
    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    CamelContext camelContext;

    private PlantEntity createTestPlant() {
        return new PlantEntity(PlantType.FLOWER, new FullPlantStats(new WaterStat(100), new SoilStat(100), new SunStat(100)));
    }

    @Test
    void testHourlyTickProcessesPlantLifecycle() throws Exception {
        // Arrange
        PlantEntity expectedPlant = createTestPlant();
        expectedPlant.grow();
        PlantEntity persistedPlant = plantRepository.save(createTestPlant());

        // Act - Simulate expected changes
        plantService.processPlantLifecycle(); // Calls triggerPlantGrowth internally
        camelContext.createProducerTemplate().sendBody(tickEndpoint, new TickMessage(TickType.HOURLY, System.currentTimeMillis()));

        // Assert
        PlantEntity updatedPlant = plantRepository.findById(persistedPlant.getId());
        assertEquals(expectedPlant.getStats(), updatedPlant.getStats(), "Plant stats should be updated correctly.");
    }

    @Test
    void testDailyTickSendsDeadPlantMessage() throws Exception {
        // Arrange
        PlantEntity deadPlant = createTestPlant();
        deadPlant.setDead(true);
        plantRepository.save(deadPlant);

        // Act
        camelContext.createProducerTemplate().sendBody(tickEndpoint, new TickMessage(TickType.DAILY, System.currentTimeMillis()));

        // Assert
        // TODO: Verify message sent to transportEndpoint (Mock or Spy?)
    }
}


