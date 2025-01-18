
package fr.pantheonsorbonne.plantlazy.manager.controller;

import fr.pantheonsorbonne.plantlazy.manager.service.MessageProducerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

@Path("/api/seeds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeedPriceController {

    @Inject
    private MessageProducerService messageProducerService;

    @POST
    @Path("/price-request")
    public Response requestSeedPrice(String seedName) {
        if (seedName == null || seedName.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Seed name cannot be null or empty.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        try {
            messageProducerService.sendMessageToRoute("direct:seed-price-queue", seedName);
            return Response.ok("{\"message\": \"Seed price request sent for: " + seedName + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to send seed price request: " + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
