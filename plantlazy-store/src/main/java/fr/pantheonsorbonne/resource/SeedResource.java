package fr.pantheonsorbonne.resource;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.exception.InsufficientFundsException;
import fr.pantheonsorbonne.exception.InsufficientStockException;
import fr.pantheonsorbonne.services.SeedService;
import fr.pantheonsorbonne.services.StoreService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("/store/seeds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeedResource {

    @Inject
    @RestClient
    StockClient stockClient;

    @Inject
    SeedService seedService;

    @Inject
    StoreService storeService;

    @GET
    public List<DailySeedOfferDTO> getAvailableSeeds() {
        List<SeedEntity> seeds = seedService.getAvailableSeeds();
        return seeds.stream()
                .map(seed -> new DailySeedOfferDTO(seed.getType(), seed.getQuality(), seed.getPrice()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/pricing-and-stock")
    public Response getSeedPricingAndStock() {
        List<PurchaseRequestDTO> pricingAndStock = seedService.getSeedPricingAndStock();
        return Response.ok(pricingAndStock).build();
    }


    @POST
    @Path("/daily-offer")
    public Response updateDailySeedOffer() {
        seedService.updateDailySeedOffer();
        return Response.ok("Daily seed offer updated").build();
    }

    @POST
    @Path("/sell")
    public Response sellSeed() {
        try {
            List<DailySeedOfferDTO> soldSeeds = seedService.sellSeed();

            if (soldSeeds.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("No seeds were available to sell.").build();
            }

            double totalPrice = soldSeeds.stream()
                    .mapToDouble(DailySeedOfferDTO::price)
                    .sum();

            Response response = stockClient.updateResource(
                    new ResourceUpdateDTO(ResourceType.MONEY, totalPrice, PlantType.OperationTag.STOCK_QUERIED)
            );

            if (response.getStatus() < 200 || response.getStatus() >= 300) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Failed to update stock service. Status: " + response.getStatus()).build();
            }


            return Response.ok(soldSeeds).build();
        } catch (InsufficientFundsException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Not enough money to complete the operation: " + e.getMessage()).build();
        } catch (InsufficientStockException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Not enough seeds available: " + e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }


}
