
package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.TickMessageDTO;
import fr.pantheonsorbonne.dto.SeedPriceRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class TickProcessor implements Processor {
    @Inject
    PlantLazyClient client;
    @Override
    public void process(Exchange exchange) throws Exception {
        List<DailySeedOfferDTO> availableSeeds = client.requestDailySeedOffer();
        RetourDeRomain moneyDisponible = client.requestAvailableMoney();

        ... machin

        List<Seed> achetees = client.acheterGraines();


        exchange.getIn().setBody(achetees);
    }
}
