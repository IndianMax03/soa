package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.dto.Success;
import com.tickets.api.tickets_service.dto.TicketResponseArray;
import com.tickets.api.tickets_service.entity.*;
import com.tickets.api.tickets_service.repository.TicketRepository;
import com.tickets.api.tickets_service.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tickets", produces = MediaType.APPLICATION_XML_VALUE)
@Slf4j
@CrossOrigin(origins = "*")
public class TicketsController {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping
    public TicketResponseArray getTickets(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(name = "isSold", required = false) Boolean isSold,
            @RequestParam(required = false) Double price,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "500") Integer pageSize,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {

        List<Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(_sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, _sort[0]));
            }
        } else {
            orders.add(new Order(sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sort[0]));
        }
        List<Ticket> tickets;
        Pageable pagingSort = PageRequest.of(page, pageSize, Sort.by(orders));
        Page<Ticket> ticketPages;

        if (id != null || name != null || isSold != null || price != null)
            ticketPages = ticketRepository.findByIdOrNameOrIsSoldOrPrice(id, name, isSold, price, pagingSort);
        else
            ticketPages = ticketRepository.findAll(pagingSort);

        tickets = ticketPages.getContent();

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
