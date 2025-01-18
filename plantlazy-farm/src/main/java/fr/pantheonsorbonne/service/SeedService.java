package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.processors.seeds.Seed;
import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dao.SeedRepository;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.SeedEntity;
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
