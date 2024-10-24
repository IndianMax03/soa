package com.booking.api.booking_service.resources;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sell")
@Produces("text/plain")
public class SellResource {

    @POST
    @Path("/{ticket-id}/{person-id}/{price}")
    public Response sell(
            @PathParam("ticket-id") Integer ticketId,
            @PathParam("person-id") Integer personId,
            @PathParam("price") Double price
    ) {
        try (Client client = ClientBuilder.newBuilder().hostnameVerifier((hostname, session) -> true).build()) {
            Response result = client
                    .target("https://tickets-service:8082/tickets_service/tickets")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();
            if (result.getStatus() == Response.Status.OK.getStatusCode() && ticketId == 1) {
                return result;
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("HAHA LOX").build();
            }
        }
    }

    @POST
    @Path("/discount/{ticket-id}/{person-id}/{discount}")
    public Response sellWithDiscount(
            @PathParam("ticket-id") Integer ticketId,
            @PathParam("person-id") Integer personId,
            @PathParam("discount") Integer discountPercent
    ) {
        return Response.ok().entity("SOLD WITH DISCOUNT = " + discountPercent + " %").build();
    }

}
