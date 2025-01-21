package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
}
