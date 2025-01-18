
package fr.pantheonsorbonne.controller;

import fr.pantheonsorbonne.service.FundsCheckService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

@Path("/api/funds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FundsCheckController {

    @Inject
    private FundsCheckService fundsCheckService;

    @POST
    @Path("/check")
    public Response checkFunds(FundsRequestDto request) {
        Boolean hasFunds = fundsCheckService.checkFunds(request.getAccountId(), request.getAmount());
        return Response.ok("{'hasFunds': " + hasFunds + "}").build();
    }

    public static class FundsRequestDto {
        private String accountId;
        private Double amount;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }
}
