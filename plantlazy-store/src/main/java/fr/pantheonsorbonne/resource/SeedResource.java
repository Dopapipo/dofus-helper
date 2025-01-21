package fr.pantheonsorbonne.resource;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.dto.SeedSaleResponseDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import fr.pantheonsorbonne.services.SeedService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/store/seeds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeedResource {

    @Inject
    StockClient stockClient;
    @Inject
    SeedService seedService;

    @GET
    public List<DailySeedOfferDTO> getAvailableSeeds() {
        List<SeedEntity> seeds = seedService.getAvailableSeeds();
        return seeds.stream()
                .map(seed -> new DailySeedOfferDTO(seed.getType(), seed.getQuantity(), seed.getPrice()))
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
    @Path("/sell/{type}/{quantity}")
    public Response sellSeed(@PathParam("type") PlantType seedType, @PathParam("quantity") int quantity) {
        try {
            Response response = stockClient.updateResource(new ResourceUpdateDTO(ResourceType.MONEY, seedService.getPriceForTypeAndQuantity(seedType, quantity), PlantType.OperationTag.STOCK_QUERIED));
            if (response.getStatus() != Response.Status.ACCEPTED.getStatusCode()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Not enough money to buy seeds").build();
            }
            seedService.sellSeed(seedType, quantity);
            return Response.ok(new SeedSaleResponseDTO(seedType, quantity, SeedQuality.HIGH)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
