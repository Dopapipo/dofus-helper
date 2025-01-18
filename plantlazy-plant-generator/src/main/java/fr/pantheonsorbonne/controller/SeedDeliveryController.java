
package fr.pantheonsorbonne.plantlazy.manager.controller;

import fr.pantheonsorbonne.plantlazy.manager.service.SeedDeliveryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

@Path("/api/seeds/delivery")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeedDeliveryController {

    @Inject
    private SeedDeliveryService seedDeliveryService;

    @POST
    public Response deliverSeeds(SeedDeliveryRequestDto request) {
        // Validate the incoming request
        if (request == null || request.getFarmId() == null || request.getFarmId().trim().isEmpty() ||
                request.getSeedType() == null || request.getSeedType().trim().isEmpty() ||
                request.getQuantity() == null || request.getQuantity() <= 0) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Invalid request. Please provide valid farmId, seedType, and quantity.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        try {
            // Invoke the service layer to process the request
            seedDeliveryService.sendSeeds(request.getFarmId(), request.getSeedType(), request.getQuantity());

            return Response.ok("{\"message\": \"Seed delivery request sent successfully.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            // Handle any exceptions during processing
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to send seed delivery request: " + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    // Static DTO class for encapsulating delivery request data
    public static class SeedDeliveryRequestDto {
        private String farmId;
        private String seedType;
        private Integer quantity;

        public String getFarmId() {
            return farmId;
        }

        public void setFarmId(String farmId) {
            this.farmId = farmId;
        }

        public String getSeedType() {
            return seedType;
        }

        public void setSeedType(String seedType) {
            this.seedType = seedType;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}

