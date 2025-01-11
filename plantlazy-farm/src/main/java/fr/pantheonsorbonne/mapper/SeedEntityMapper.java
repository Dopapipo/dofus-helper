package fr.pantheonsorbonne.mapper;

import fr.pantheonsorbonne.camel.processors.seeds.Seed;
import fr.pantheonsorbonne.entity.SeedEntity;

public class SeedEntityMapper {
   public static SeedEntity toEntity(Seed seed) {
        return new SeedEntity(seed.getType(), seed.getQuality(), seed.getInitialStats());
    }

}
