package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PlantDAOImpl implements PlantDAO {

    @Inject
    EntityManager entityManager;

    @Override
    public List<PlantEntity> getAllPlants() {
        return entityManager.createQuery("SELECT p FROM PlantEntity p", PlantEntity.class).getResultList();
    }

    @Override
    public Optional<PlantEntity> getPlantByType(String type) {
        return entityManager.createQuery("SELECT p FROM PlantEntity p WHERE p.type = :type", PlantEntity.class)
                .setParameter("type", type)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public void updatePlant(PlantEntity plant) {
        entityManager.merge(plant);
    }

    @Override
    @Transactional
    public void savePlant(PlantEntity plant) {
        entityManager.persist(plant);
    }
}
