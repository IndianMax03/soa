package com.tickets.api.tickets_service.service;

import com.tickets.api.tickets_service.entity.Ticket;
import com.tickets.api.tickets_service.entity.Venue;

import java.util.List;
import java.util.Set;

public interface TicketService {

    List<Ticket> getTickets();

    Ticket createTicket(Ticket ticket);

    Ticket getTicket(Long id);

    Ticket updateTicket(Long id, Ticket ticket);

    void removeTicket(Long id);

    double getPriceSum();

    Ticket getTicketWithMinimumVenue();

    Set<Venue> getUniqueVenues();

}
