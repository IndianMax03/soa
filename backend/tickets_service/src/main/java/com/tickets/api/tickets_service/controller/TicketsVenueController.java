package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.entity.Ticket;
import com.tickets.api.tickets_service.entity.Venue;
import com.tickets.api.tickets_service.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/tickets/venue")
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
    public Set<Venue> getTicketsVenueUnique() {
        return ticketService.getUniqueVenues();
    }

}
