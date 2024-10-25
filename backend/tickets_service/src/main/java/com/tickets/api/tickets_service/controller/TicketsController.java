package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.dto.Success;
import com.tickets.api.tickets_service.dto.TicketResponseArray;
import com.tickets.api.tickets_service.entity.Ticket;
import com.tickets.api.tickets_service.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tickets", produces = MediaType.APPLICATION_XML_VALUE)
@Slf4j
@CrossOrigin(origins = "*")
public class TicketsController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public TicketResponseArray getTickets() {
        List<Ticket> tickets = ticketService.getTickets();
        TicketResponseArray response = new TicketResponseArray();
        response.setTickets(tickets);
        return response;
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
    public Success deleteTicket(@PathVariable long id) {
        ticketService.removeTicket(id);
        Success response = new Success();
        response.setCode(HttpStatus.NO_CONTENT.value());
        response.setMessage("Ticket with id = " + id + " successfully deleted!!!");
        return response;
    }

}
