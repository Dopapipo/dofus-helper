package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.ResourceResponseDTO;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "stock-service")
@Path("/api/stock")
public interface StockClient {

    @POST
    @Path("/update")
    void updateStock(ResourceResponseDTO resourceUpdate);
}



