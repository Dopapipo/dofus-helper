package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.ErrorResponse;
import fr.pantheonsorbonne.entity.ResourceType;
import fr.pantheonsorbonne.exception.ResourceException;
import fr.pantheonsorbonne.service.StockService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockResource {
    @Inject
    StockService stockService;

    @POST
    @Path("/{resourceType}/request")
    public Response requestResource(
            @PathParam("resourceType") ResourceType type,
            @QueryParam("quantity") Double quantity
    ) {
        try {
            stockService.updateResource(type, -quantity);
            return Response.ok().build();
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