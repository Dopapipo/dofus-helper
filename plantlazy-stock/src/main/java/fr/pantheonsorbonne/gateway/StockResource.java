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
    private StockService stockService;

    @POST
    @Path("/update")
    public Response updateResource(ResourceDTO resourceDTO) {
        try {
            String operationTag = resourceDTO.getOperationTag();
            ResourceDTO updatedResource = stockService.updateResource(resourceDTO.getType(), resourceDTO.getQuantity(), operationTag);
            return Response.ok(updatedResource).build();
        } catch (ResourceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e))
                    .build();
        }
    }
}
