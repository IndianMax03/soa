package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.entity.Ticket;
import com.tickets.api.tickets_service.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@Slf4j
public class TicketsController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        log.info(ticket.toString());
        return ticketService.createTicket(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable long id) {
        return ticketService.getTicket(id);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable long id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable long id) {
        ticketService.removeTicket(id);
        return "Ticket with id = " + id + " successfully deleted!!!";
    }

}
