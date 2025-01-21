package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ResourceDAOImplTest {

    @Inject
    ResourceDAO resourceDAO;

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void testSaveResource() {
        Resource resource = new Resource(ResourceType.WATER, 100);

        resourceDAO.save(resource);
        em.flush();

        Resource persistedResource = resourceDAO.findById(resource.getId());
        assertNotNull(persistedResource);
        assertEquals(ResourceType.WATER, persistedResource.getType());
        assertEquals(100, persistedResource.getQuantity());
    }

    @Test
    @Transactional
    void testFindAllResources() {
        Resource resource1 = new Resource(ResourceType.WATER, 100);
        Resource resource2 = new Resource(ResourceType.ENERGY, 50);
        resourceDAO.save(resource1);
        resourceDAO.save(resource2);
        em.flush();

        Iterable<Resource> resources = resourceDAO.findAll();

        assertNotNull(resources);
        assertTrue(resources.iterator().hasNext());
    }
}
