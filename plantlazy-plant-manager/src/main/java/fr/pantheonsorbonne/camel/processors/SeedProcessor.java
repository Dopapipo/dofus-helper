
package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.dto.SeedSaleDTO;
import fr.pantheonsorbonne.dto.TickMessageDTO;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.service.StoreClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SeedProcessor implements Processor {

    @Inject
    StoreClient storeClient;

    @Override
    public void process(Exchange exchange) throws Exception {
        SeedSaleDTO seedSaleDTO = exchange.getIn().getBody(SeedSaleDTO.class);

        List<SeedDTO> seedMessages = mapSeedSaleDtoToMessages(seedSaleDTO);

        exchange.getIn().setBody(seedMessages);
    }

    private List<SeedDTO> mapSeedSaleDtoToMessages(SeedSaleDTO seedSaleDTO) {
        return IntStream.range(0, seedSaleDTO.quantity())
                .mapToObj(i -> new SeedDTO(seedSaleDTO.seedType(), seedSaleDTO.quality()))
                .collect(Collectors.toList());
    }

}
