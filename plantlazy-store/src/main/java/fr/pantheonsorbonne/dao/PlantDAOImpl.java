package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PlantDAOImpl implements PlantDAO {

    @Inject
    EntityManager entityManager;

    @Override
    public List<PlantEntity> getAllPlants() {
        return entityManager.createQuery("SELECT p FROM PlantEntity p", PlantEntity.class).getResultList();
    }

    @Override
    @Transactional
    public void savePlant(PlantEntity plant) {
        entityManager.persist(plant);
    }

    @Override
    @Transactional
    public void deletePlantById(UUID plantId) {
        PlantEntity plant = entityManager.find(PlantEntity.class, plantId);
        if (plant != null) {
            entityManager.remove(plant);
        } else {
            throw new IllegalArgumentException("Aucune plante trouvée avec l'ID : " + plantId);
        }
    }

}
