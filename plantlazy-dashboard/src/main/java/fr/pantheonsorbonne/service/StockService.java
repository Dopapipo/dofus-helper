package fr.pantheonsorbonne.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import fr.pantheonsorbonne.dto.InitMoneyDTO;

@Path("/api")
@RegisterRestClient(configKey = "stock-service")
public interface StockService {

    @POST
    @Path("/stock-init")
    @Consumes(MediaType.APPLICATION_JSON)
    void initializeMoney(InitMoneyDTO money);
}
