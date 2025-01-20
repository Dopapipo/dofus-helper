package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.ResourceResponseDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "stock-service")
@Path("/api/stock/")
public interface StockService {

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateResource(ResourceResponseDTO resourceDTO);

    @GET
    @Path("/money")
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResponseDTO getMoney();
}
