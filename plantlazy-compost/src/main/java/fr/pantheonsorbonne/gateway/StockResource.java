package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.ErrorResponse;
import fr.pantheonsorbonne.dto.ResourceDTO;
import fr.pantheonsorbonne.exception.ResourceException;
import fr.pantheonsorbonne.service.StockService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockResource {
    @Inject
    StockService stockService;

    @POST
    @Path("/request")
    public Response requestResource(ResourceDTO resourceDTO) {
        try {
            ResourceDTO updatedResource = stockService.updateResource(resourceDTO.getType(), -resourceDTO.getQuantity());
            return Response.ok(updatedResource).build();
        } catch (ResourceException e) {
            return Response.status(getStatusCode(e))
                    .entity(new ErrorResponse(e))
                    .build();
        }
    }

    private int getStatusCode(ResourceException e) {
        return switch (e.getCode()) {
            case INSUFFICIENT_RESOURCE -> 400;
            case DAILY_LIMIT_EXCEEDED -> 429;
            case RESOURCE_NOT_FOUND -> 404;
            case INVALID_QUANTITY -> 400;
            case DATABASE_ERROR -> 500;
            default -> 500;
        };

    }
}