package com.booking.api.booking_service.resources;

import com.booking.api.booking_service.entity.Address;
import com.booking.api.booking_service.entity.Ticket;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Level;

@Path("/sell")
@Produces("application/xml")
public class SellResource {

    @POST
    @Path("/{ticket-id}/{person-id}/{price}")
    public Response sell(
            @PathParam("ticket-id") Integer ticketId,
            @PathParam("person-id") Integer personId,
            @PathParam("price") Double price
    ) {
        try (Client client = ClientBuilder.newBuilder().hostnameVerifier((hostname, session) -> true).build()) {
            Response ticketsResponse = client
                    .target("https://tickets-service:8082/tickets_service/tickets/1")
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .get();

//            List<Ticket> tickets = ticketsResponse.readEntity(new GenericType<List<Ticket>>(){});
            Ticket tickets = ticketsResponse.readEntity(Ticket.class);
            return Response.ok().entity(tickets).build();
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
