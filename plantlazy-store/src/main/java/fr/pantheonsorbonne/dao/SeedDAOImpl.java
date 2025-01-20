package fr.pantheonsorbonne.dao;


import fr.pantheonsorbonne.entity.SeedEntity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SeedDAOImpl implements SeedDAO {

    @Inject
    EntityManager entityManager;

    @Override
    public List<SeedEntity> getAllSeeds() {
        return entityManager.createQuery("SELECT s FROM SeedEntity s", SeedEntity.class).getResultList();
    }

    @Override
    public Optional<SeedEntity> getSeedByType(PlantType type) {
        return entityManager.createQuery("SELECT s FROM SeedEntity s WHERE s.type = :type", SeedEntity.class)
                .setParameter("type", type)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public void updateSeed(SeedEntity seed) {
        entityManager.merge(seed);
    }

    @Override
    @Transactional
    public void saveSeed(SeedEntity seed) {
        entityManager.persist(seed);
    }
}
