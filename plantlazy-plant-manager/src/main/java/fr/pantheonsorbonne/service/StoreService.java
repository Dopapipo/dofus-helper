package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PlantType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "store-service")
@Path("/store")
public interface StoreService {

    @GET
    @Path("/seeds")
    @Consumes(MediaType.APPLICATION_JSON)
    List<DailySeedOfferDTO> getAvailableSeeds();

    @POST
    @Path("/seeds/sell/{type}/{quantity}")
    @Consumes(MediaType.APPLICATION_JSON)
    void sellSeed(@PathParam("type") PlantType type, @PathParam("quantity") int quantity);


}
