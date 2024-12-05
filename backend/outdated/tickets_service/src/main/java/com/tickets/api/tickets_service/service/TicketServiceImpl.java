package com.tickets.api.tickets_service.service;

import com.tickets.api.tickets_service.entity.Ticket;
import com.tickets.api.tickets_service.entity.Venue;
import com.tickets.api.tickets_service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow();
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    @Override
    public void removeTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public double getPriceSum() {
        return ticketRepository.findAll()
                .stream()
                .mapToDouble(Ticket::getPrice)
                .sum();
    }

    @Override
    public Ticket getTicketWithMinimumVenue() {
        return ticketRepository.findAll()
                .stream()
                .max(Comparator.comparing(Ticket::getVenue))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<Venue> getUniqueVenues() {
        return ticketRepository.findAll()
                .stream()
                .map(Ticket::getVenue)
                .collect(Collectors.toSet());
    }
}
