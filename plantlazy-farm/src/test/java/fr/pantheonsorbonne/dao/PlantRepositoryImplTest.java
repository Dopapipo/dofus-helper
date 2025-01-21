package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class PlantRepositoryImplTest {

    @Inject
    PlantRepository plantRepository;
    @Inject
    EntityManager em;
    @Transactional
    @Test
    public void testSavePlant() {
        PlantEntity plant = new PlantEntity(PlantType.FLOWER, new FullPlantStats(
                new WaterStat(100),
                new SoilStat(100),
                new SunStat(100)
        ));

        plantRepository.save(plant);
        em.flush();

        PlantEntity retrievedPlant = plantRepository.findById(plant.getId());
        assertNotNull(retrievedPlant, "Plant should be saved in the database");
        assertNotNull(retrievedPlant.getWater());
        assertNotNull(retrievedPlant.getSoil());
        assertNotNull(retrievedPlant.getSun());
    }

    @Transactional
    @Test
    @Disabled
    public void testFindAllWithOnePlant() {
        // Create and save a new plant
        PlantEntity plant = new PlantEntity(PlantType.FLOWER, new FullPlantStats(
                new WaterStat(100),
                new SoilStat(100),
                new SunStat(100)
        ));

        PlantEntity savedPlant = plantRepository.save(plant);
        em.flush();

        // Retrieve all plants
        Iterable<PlantEntity> plants = plantRepository.findAll();
        List<PlantEntity> plantList = StreamSupport.stream(plants.spliterator(), false).toList();

        // Assertions
        assertFalse(plantList.isEmpty(), "findAll() should return at least one plant");
        assertTrue(plantList.contains(savedPlant), "Saved plant should be in the result list");

        // Validate that the retrieved plant has all stats
        PlantEntity retrievedPlant = plantList.getFirst();
        assertNotNull(retrievedPlant.getId(), "Plant ID should not be null");
        assertNotNull(retrievedPlant.getWater(), "WaterStat should not be null");
        assertNotNull(retrievedPlant.getSoil(), "SoilStat should not be null");
        assertNotNull(retrievedPlant.getSun(), "SunStat should not be null");
    }




}
