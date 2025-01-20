
package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.camel.client.PlantLazyClient;
import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PlantType;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.entity.SeedType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Comparator;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jgroups.util.Tuple;

@ApplicationScoped
public class TickProcessor implements Processor {
    @Inject
    PlantLazyClient client;

    @Override
    public void process(Exchange exchange) throws Exception {
        List<DailySeedOfferDTO> availableSeeds = client.requestDailySeedOffer();
        Integer moneyAvailable = client.requestMoneyAvailable().quantity();

        availableSeeds.sort(Comparator.comparingDouble(DailySeedOfferDTO::price));

        Tuple<Integer,PlantType> bestBuy = getMaxBuyable(availableSeeds, moneyAvailable);
        PurchaseRequestDTO purchaseRequest = new PurchaseRequestDTO(bestBuy.getVal2(), bestBuy.getVal1());
        client.buySeed(purchaseRequest);
    }

    private Tuple<Integer,PlantType> getMaxBuyable(List<DailySeedOfferDTO> availableSeeds, int moneyAvailable) {
        availableSeeds.sort(Comparator.comparingDouble(DailySeedOfferDTO::price));
        int maxBuyable = 0;
        PlantType seedType = null;
        for(DailySeedOfferDTO seed : availableSeeds) {
            int maxAffordable = (int) (moneyAvailable / seed.price());
            int toBuy = Math.min(maxAffordable, seed.quantity());
            if(toBuy > maxBuyable) {
                maxBuyable = toBuy;
                seedType = seed.seedType();
            }
        }
        return new Tuple<>(maxBuyable, seedType);
    }
}
