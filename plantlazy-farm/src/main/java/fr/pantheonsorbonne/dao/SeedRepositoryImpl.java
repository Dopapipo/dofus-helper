package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.SeedEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SeedRepositoryImpl implements SeedRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(SeedEntity seed) {
        if (seed == null) {
            throw new IllegalArgumentException("SeedEntity cannot be null");
        }
        entityManager.persist(seed);
    }
}
