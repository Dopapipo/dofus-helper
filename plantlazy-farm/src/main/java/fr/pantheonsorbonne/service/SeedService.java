package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.seed.Seed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SeedService {
    @Inject
    PlantRepository plantRepository;

    public PlantEntity growSeed(SeedDTO seedDTO) {
        Seed seed = seedDTO.toSeed();
        PlantEntity plant = seed.grow();
        return plantRepository.save(plant);
    }
}
