package com.booking.api.booking_service.resources;

import com.booking.api.booking_service.dto.PersonResponseArray;
import com.booking.api.booking_service.dto.TicketResponseArray;
import com.booking.api.booking_service.entity.Person;
import com.booking.api.booking_service.entity.Ticket;
import com.booking.api.booking_service.service.ClientService;
import com.booking.api.booking_service.service.TicketWindowService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Level;

@Path("/sell")
@Produces("application/xml")
public class SellResource {

    @Inject
    TicketWindowService ticketWindowService;

    @POST
    @Path("/{ticket-id}/{person-id}/{price}")
    public Response sell(
            @PathParam("ticket-id") Integer ticketId,
            @PathParam("person-id") Integer personId,
            @PathParam("price") Double price
    ) {
        Person updatedPerson = ticketWindowService.sellTicketToPerson(ticketId, personId, price);
        return Response.ok().entity(updatedPerson).build();
    }

    @POST
    @Path("/discount/{ticket-id}/{person-id}/{discount}")
    public Response sellWithDiscount(
            @PathParam("ticket-id") Integer ticketId,
            @PathParam("person-id") Integer personId,
            @PathParam("discount") Integer discountPercent
    ) {
        Person updatedPerson = ticketWindowService.sellTicketToPersonWithDiscount(ticketId, personId, discountPercent);
        return Response.ok().entity(updatedPerson).build();
    }

}
