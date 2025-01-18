package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.camel.processors.seeds.GenericSeed;
import fr.pantheonsorbonne.camel.processors.seeds.Seed;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.mapper.SeedEntityMapper;
import fr.pantheonsorbonne.service.SeedService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
@ApplicationScoped
public class SeedProcessor implements Processor {

    @Inject
    SeedService seedService;
    @Override
    public void process(Exchange exchange) throws Exception {
        SeedDTO seedDTO = exchange.getIn().getBody(SeedDTO.class);
        seedService.growSeed(seedDTO);
    }
}

