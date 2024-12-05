package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.dto.VenueResponseArray;
import com.tickets.api.tickets_service.entity.Ticket;
import com.tickets.api.tickets_service.entity.Venue;
import com.tickets.api.tickets_service.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/tickets/venue", produces = MediaType.APPLICATION_XML_VALUE)
@Slf4j
@CrossOrigin(origins = "*")
public class TicketsVenueController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/min")
    public Ticket getTicketsVenueMin() {
        return ticketService.getTicketWithMinimumVenue();
    }

    @GetMapping("/unique")
    public VenueResponseArray getTicketsVenueUnique() {
        Set<Venue> result = ticketService.getUniqueVenues();
        VenueResponseArray response = new VenueResponseArray();
        response.setVenues(result);
        return response;
    }

}
