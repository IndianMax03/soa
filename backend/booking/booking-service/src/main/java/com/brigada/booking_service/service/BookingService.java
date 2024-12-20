package com.brigada.booking_service.service;

import com.brigada.general.model.dto.PersonDto;
import com.brigada.general.model.dto.TicketDto;
import com.brigada.general.model.soap.SellRequest;
import com.brigada.general.model.soap.SellWithDiscountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private final TicketsService ticketsService;

    public SellRequest sellTicketToPerson(Long ticketId, Long personId, Double price) {
        SellRequest response = new SellRequest(ticketId, personId, price, null);
        List<PersonDto> persons = ticketsService.getAllPersons();
        List<TicketDto> tickets = ticketsService.getAllTickets();

        Optional<PersonDto> targetPerson = persons.stream()
                .filter(personDto -> personDto.getId() == personId)
                .findFirst();
        if (targetPerson.isEmpty()) {
            response.setResult("Person with id = " + personId + " doesn't exists!");
            return response;
        }

        Optional<TicketDto> targetTicket = tickets.stream()
                .filter(ticketDto -> ticketDto.getId() == ticketId)
                .findFirst();
        if (targetTicket.isEmpty()) {
            response.setResult("Ticket with id = " + ticketId + " doesn't exists!");
            return response;
        }

        PersonDto person = targetPerson.get();
        TicketDto ticket = targetTicket.get();

        if (ticket.isSold()) {
            response.setResult("Can't sell ticket because ticket already sold!");
            return response;
        }

        if (person.getBalance() < price) {
            response.setResult("Not enough money!");
            return response;
        }
        person.setBalance(person.getBalance() - price);
        ticket.setOwner(person);
        ticket.setSold(true);
        ticketsService.updateTicket(ticketId, ticket);
        response.setResult("ok");
        return response;
    }

    public SellWithDiscountRequest sellTicketToPersonWithDiscount(Long ticketId, Long personId, Integer discountPercent) {
        SellWithDiscountRequest response = new SellWithDiscountRequest(ticketId, personId, discountPercent, null);
        List<PersonDto> persons = ticketsService.getAllPersons();
        List<TicketDto> tickets = ticketsService.getAllTickets();

        Optional<PersonDto> targetPerson = persons.stream()
                .filter(personDto -> personDto.getId() == personId)
                .findFirst();
        if (targetPerson.isEmpty()) {
            response.setResult("Person with id = " + personId + " doesn't exists!");
            return response;
        }

        Optional<TicketDto> targetTicket = tickets.stream()
                .filter(ticketDto -> ticketDto.getId() == ticketId)
                .findFirst();
        if (targetTicket.isEmpty()) {
            response.setResult("Ticket with id = " + ticketId + " doesn't exists!");
            return response;
        }

        PersonDto person = targetPerson.get();
        TicketDto ticket = targetTicket.get();

        if (ticket.isSold()) {
            response.setResult("Can't sell ticket because ticket already sold!");
            return response;
        }

        double price = ticket.getPrice() * (100 - discountPercent) / 100;

        if (person.getBalance() < price) {
            response.setResult("Not enough money!");
            return response;
        }
        person.setBalance(person.getBalance() - price);
        ticket.setOwner(person);
        ticket.setSold(true);
        ticketsService.updateTicket(ticketId, ticket);
        response.setResult("ok");
        return response;
    }

}
