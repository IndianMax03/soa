package com.brigada.booking_service.service;

import com.brigada.general.model.dto.PersonDto;
import com.brigada.general.model.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private final TicketsService ticketsService;

    public ResponseEntity<String> sellTicketToPerson(Long ticketId, Long personId, Double price, Integer discountPercent) {
        List<PersonDto> persons = ticketsService.getAllPersons();
        List<TicketDto> tickets = ticketsService.getAllTickets();

        Optional<PersonDto> targetPerson = persons.stream()
                .filter(personDto -> personDto.getId() == personId)
                .findFirst();
        if (targetPerson.isEmpty()) {
            return ResponseEntity.badRequest().body("Person with id = " + personId + " doesn't exists!");
        }

        Optional<TicketDto> targetTicket = tickets.stream()
                .filter(ticketDto -> ticketDto.getId() == ticketId)
                .findFirst();
        if (targetTicket.isEmpty()) {
            return ResponseEntity.badRequest().body("Ticket with id = " + ticketId + " doesn't exists!");
        }

        PersonDto person = targetPerson.get();
        TicketDto ticket = targetTicket.get();

        if (ticket.isSold()) {
            return ResponseEntity.badRequest().body("Can't sell ticket because ticket already sold!");
        }

        if (discountPercent != null) {
            price = ticket.getPrice() * (100 - discountPercent) / 100;
        }

        if (person.getBalance() < price) {
            return ResponseEntity.badRequest().body("Not enough money!");
        }
        person.setBalance(person.getBalance() - price);
        ticket.setOwner(person);
        ticket.setSold(true);
        ticketsService.updateTicket(ticketId, ticket);
        return ResponseEntity.ok().build();
    }

}
