package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ResourceDAOImpl implements ResourceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Resource> findByType(ResourceType type) {
        return entityManager.createQuery("SELECT r FROM Resource r WHERE r.type = :type", Resource.class)
                .setParameter("type", type)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public List<Resource> findAll() {
        return entityManager.createQuery("SELECT r FROM Resource r", Resource.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Resource findById(Long id) {
        return entityManager.find(Resource.class, id);
    }


    @Override
    @Transactional
    public Resource save(Resource resource) {
        if (resource.getId() == null) {
            entityManager.persist(resource);
            return resource;
        } else {
            return entityManager.merge(resource);
        }
    }
}