package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.entity.seed.Seed;
import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SeedService {
    @Inject
    PlantRepository plantRepository;

    public void growSeed(SeedDTO seedDTO) {
        Seed seed = seedDTO.toSeed();
        PlantEntity plant = seed.grow();
        plantRepository.save(plant);
    }
}
