package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.camel.processors.seeds.GenericSeed;
import fr.pantheonsorbonne.camel.processors.seeds.Seed;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.mapper.SeedEntityMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SeedProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        SeedDTO seedDTO = exchange.getIn().getBody(SeedDTO.class);

        Seed seed = new GenericSeed(seedDTO.getType(), seedDTO.getQuality());

        SeedEntity seedEntity = SeedEntityMapper.toEntity(seed);

        exchange.getIn().setBody(seedEntity);
    }
}

