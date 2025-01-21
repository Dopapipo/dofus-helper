package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.dto.ResourceLevelDTO;
import fr.pantheonsorbonne.entity.enums.ResourceType;
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
    @Path("/update")
    public Response updateResource(ResourceUpdateDTO resourceUpdateDTO) {
        try {
            ResourceUpdateDTO resourceUpdated = stockService.updateResource(resourceUpdateDTO);
            return Response.accepted(resourceUpdated).build();
        } catch (ResourceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @GET
    @Path("/{resourceType}")
    public Response getResourceValue(@PathParam("resourceType") ResourceType resourceType) {
        try {
            ResourceLevelDTO resourceLevelDTO = stockService.getResourceValue(resourceType);
            return Response.ok(resourceLevelDTO).build();
        } catch (ResourceException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }


}