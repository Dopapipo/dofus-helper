package fr.pantheonsorbonne.resource;

import fr.pantheonsorbonne.services.SeedService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/store/seeds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeedResource {

    @Inject
    SeedService seedService;


    @POST
    @Path("/daily-offer")
    public Response updateDailySeedOffer() {
        seedService.updateDailySeedOffer();
        return Response.ok("Daily seed offer updated").build();
    }


}
