package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class PlantManagerImpl implements PlantManager {
    @Inject
    ProducerTemplate producerTemplate;
    @Inject
    LogService logService;
    @Inject
    PlantRessourceManager plantRessourceManager;
    @Inject
    PlantRepository plantRepository;

    @Override
    public void sendDeadPlants(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            if (plant.isDead() && !plant.getComposted()) {
                try {
                    this.sendDead(plant);
                    logService.sendLog(PlantMapper.toPlantDiedLog(plant));
                    plant.setComposted(true);
                    plantRepository.save(plant);
                } catch (Exception e) {
                }
            }
        }
    }

        @Override
        public void triggerPlantNourishment (Iterable < PlantEntity > plants) {
            for (PlantEntity plant : plants) {
                for (PlantStat stat : plant.getStats()) {
                    if (plantNeedsNourishmentForStat(plant, stat)) {
                        int requiredResourceQuantity = stat.getOptimalRessourceQuantityToFeed();
                        PlantEntity updatedPlant = plantRessourceManager.feedPlant(plant, requiredResourceQuantity, stat);
                        plantRepository.save(updatedPlant);
                        logService.sendLog(PlantMapper.toPlantUpdatedLog(updatedPlant));
                    }
                }
            }
        }

        @Override
        public void triggerPlantGrowth (Iterable < PlantEntity > plants) {
            for (PlantEntity plant : plants) {
                if (!plant.isDead()) {
                    plant.grow();
                    PlantEntity updatedPlant = plantRepository.save(plant);
                    logService.sendLog(PlantMapper.toPlantUpdatedLog(updatedPlant));
                }
            }
        }

        private boolean plantNeedsNourishmentForStat (PlantEntity plant, PlantStat plantStat){
            return (!plant.isDead() && plant.getRemainingTicksOfHealthyFor(plantStat) < 3);
        }

    private void sendDead(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        producerTemplate.sendBody("direct:plantQueue", plantDTO);
    }
    }
