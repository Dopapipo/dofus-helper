package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.SeedDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SendingSeedService {

    @Inject
    @ConfigProperty(name = "plant.seed.endpoint")
    String seedEndpoint;

    @Inject
    SeedDAO seedDAO;

    @Inject
    ProducerTemplate producerTemplate;


    public void sendAllSeedsToQueue() {
        seedDAO.getAllSeeds()
                .forEach(seed -> {
                    producerTemplate.sendBody(seedEndpoint, seed);
                });
    }
}
