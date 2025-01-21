package fr.pantheonsorbonne.resource;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.exception.InsufficientStockException;
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
    @Path("/sell/{quantity}")
    public Response sellSeed(@PathParam("quantity") int quantity) {
        try {
            // Vendre les graines via le service et récupérer la liste des graines vendues
            List<DailySeedOfferDTO> soldSeeds = seedService.sellSeed(quantity);

            // Vérifier si des graines ont été vendues
            if (soldSeeds.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("No seeds were available to sell.").build();
            }

            // Calculer le prix total des graines à vendre
            double totalPrice = seedService.getCalculateTotalPrice(quantity);

            // Appeler le microservice Stock pour mettre à jour les ressources (argent)
            Response response = stockClient.updateResource(
                    new ResourceUpdateDTO(ResourceType.MONEY, totalPrice, PlantType.OperationTag.STOCK_QUERIED)
            );

            // Vérifier la réponse du microservice Stock
            if (response.getStatus() != Response.Status.ACCEPTED.getStatusCode()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Not enough money to buy seeds").build();
            }

            // Créer une réponse avec succès contenant la liste des graines vendues
            return Response.ok(soldSeeds).build();
        } catch (InsufficientStockException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Not enough seeds available: " + e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

}
