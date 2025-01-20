
package fr.pantheonsorbonne.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class StoreClient {

    private static final String STORE_URL = "http://plantlazy-store/seeds";

    public List<Map<String, Object>> getAvailableSeeds() {
        Client client = ClientBuilder.newClient();
        try {
            return client.target(STORE_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Map<String, Object>>>() {});
        } finally {
            client.close();
        }
    }
}
