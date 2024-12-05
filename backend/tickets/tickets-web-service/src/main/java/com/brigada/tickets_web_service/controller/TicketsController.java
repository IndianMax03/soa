package com.brigada.tickets_web_service.controller;

import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.model.Ticket;
import com.brigada.tickets_web_service.service.TicketsService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketsController {

    @Inject
    private TicketsService ticketsService;

    @GET
    public Response getTickets(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sort") List<String> sortList,
            @QueryParam("id") String idValue,
            @QueryParam("id-filter") String idFilter,
            @QueryParam("name") String nameValue,
            @QueryParam("name-filter") String nameFilter,
            @QueryParam("coordinates.x") String coordinatesXValue,
            @QueryParam("coordinates.x-filter") String coordinatesXFilter,
            @QueryParam("coordinates.y") String coordinatesYValue,
            @QueryParam("coordinates.y-filter") String coordinatesYFilter,
            @QueryParam("creation_date") String creationDateValue,
            @QueryParam("creation_date-filter") String creationDateFilter,
            @QueryParam("is_sold") String isSoldValue,
            @QueryParam("is_sold-filter") String isSoldFilter,
            @QueryParam("price") String priceValue,
            @QueryParam("price-filter") String priceFilter,
            @QueryParam("type") String typeValue,
            @QueryParam("type-filter") String typeFilter,
            @QueryParam("venue.id") String venueIdValue,
            @QueryParam("venue.id-filter") String venueIdFilter,
            @QueryParam("venue.name") String venueNameValue,
            @QueryParam("venue.name-filter") String venueNameFilter,
            @QueryParam("venue.capacity") String venueCapacityValue,
            @QueryParam("venue.capacity-filter") String venueCapacityFilter,
            @QueryParam("venue.type") String venueTypeValue,
            @QueryParam("venue.type-filter") String venueTypeFilter,
            @QueryParam("venue.address.id") String venueAddressIdValue,
            @QueryParam("venue.address.id-filter") String venueAddressIdFilter,
            @QueryParam("venue.address.zip_code") String venueAddressZipCodeValue,
            @QueryParam("venue.address.zip_code-filter") String venueAddressZipCodeFilter
    ) {
        PageDto<Ticket> tickets = ticketsService.findAll(
                page, size, sortList,
                idValue, idFilter,
                nameValue, nameFilter,
                coordinatesXValue, coordinatesXFilter,
                coordinatesYValue, coordinatesYFilter,
                creationDateValue, creationDateFilter,
                isSoldValue, isSoldFilter,
                priceValue, priceFilter,
                typeValue, typeFilter,
                venueIdValue, venueIdFilter,
                venueNameValue, venueNameFilter,
                venueCapacityValue, venueCapacityFilter,
                venueTypeValue, venueTypeFilter,
                venueAddressIdValue, venueAddressIdFilter,
                venueAddressZipCodeValue, venueAddressZipCodeFilter
        );
        return Response.ok(tickets).build();
    }

    @GET
    @Path("/{id}")
    public Response getTicket(@PathParam("id") Long id) {
        Ticket ticket = ticketsService.findById(id);
        return Response.ok(ticket).build();
    }

    @POST
    public Response createTicket(@Valid Ticket ticket) {
        ticketsService.save(ticket);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTicket(@PathParam("id") Long id, @Valid Ticket ticket) {
        ticketsService.update(id, ticket);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") Long id) {
        ticketsService.delete(id);
        return Response.ok().build();
    }

}
