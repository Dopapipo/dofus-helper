package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ResourceInitializerService {

    @Inject
    ResourceDAO resourceDAO;

    @Transactional
    public void initializeResources(Double initialMoney) {
        initializeResource(ResourceType.WATER, 50.0);
        initializeResource(ResourceType.ENERGY, 50.0);
        initializeResource(ResourceType.FERTILIZER, 200.0);
        initializeResource(ResourceType.MONEY, initialMoney);

    }

    private void initializeResource(ResourceType type, double initialQuantity) {
        Resource resource = new Resource();
        resource.setType(type);
        resource.setQuantity(initialQuantity);
        resourceDAO.save(resource);
    }
}
