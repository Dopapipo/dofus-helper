package fr.pantheonsorbonne.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;
import fr.pantheonsorbonne.exception.DatabaseException;
import java.util.Optional;

@ApplicationScoped
public class ResourceDAO {
    @Inject
    EntityManager em;

    public Optional<Resource> findByType(ResourceType type) {
        try {
            TypedQuery<Resource> query = em.createQuery(
                    "SELECT r FROM Resource r WHERE r.type = :type",
                    Resource.class
            );
            query.setParameter("type", type);
            return query.getResultStream().findFirst();
        } catch (PersistenceException e) {
            throw new DatabaseException("Error retrieving resource", e);
        }
    }

    @Transactional
    public void save(Resource resource) {
        try {
            if (resource.getId() == null) {
                em.persist(resource);
                return;
            }
            em.merge(resource);
        } catch (PersistenceException e) {
            throw new DatabaseException("Error saving resource", e);
        }
    }
}
