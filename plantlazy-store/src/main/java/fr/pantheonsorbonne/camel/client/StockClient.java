package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.dto.ResourceMoneyDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "stock-service")
@Path("/api/stock/")
public interface StockClient {

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateResource(ResourceUpdateDTO resourceDTO);

    @GET
    @Path("/{resourceType}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getResourceByType(@PathParam("resourceType") ResourceType resourceType);

}
