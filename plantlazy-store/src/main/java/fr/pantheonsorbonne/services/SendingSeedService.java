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
    SeedDAO seedDAO; // DAO pour récupérer les graines

    @Inject
    ProducerTemplate producerTemplate; // Template Camel pour envoyer les graines


    public void sendAllSeedsToQueue() {
        seedDAO.getAllSeeds()
                .forEach(seed -> {
                    producerTemplate.sendBody(seedEndpoint, seed);
                });
    }
}
