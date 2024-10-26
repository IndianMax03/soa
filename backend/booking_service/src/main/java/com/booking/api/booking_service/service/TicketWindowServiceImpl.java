package com.booking.api.booking_service.service;


import com.booking.api.booking_service.dto.PersonResponseArray;
import com.booking.api.booking_service.dto.TicketResponseArray;
import com.booking.api.booking_service.entity.Person;
import com.booking.api.booking_service.entity.Ticket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TicketWindowServiceImpl implements TicketWindowService {

    @Inject
    ClientService clientService;

    @Override
    public Person sellTicketToPerson(Integer ticketId, Integer personId, Double price) {
        List<Person> persons = clientService.getAllPersons();
        List<Ticket> tickets = clientService.getAllTickets();

        Person targetPerson = persons.stream().filter(p -> p.getId() == personId).findFirst().orElseThrow();
        Ticket targetTicket = tickets.stream().filter(t -> t.getId() == ticketId).findFirst().orElseThrow();
        targetPerson.setBalance(targetPerson.getBalance() - price);
        targetTicket.setSold(true);
        targetPerson.getTickets().add(targetTicket);

        Person updatedPerson = clientService.updatePerson(targetPerson);
        return updatedPerson;
    }

    @Override
    public Person sellTicketToPersonWithDiscount(Integer ticketId, Integer personId, Integer discountPercent) {
        List<Person> persons = clientService.getAllPersons();
        List<Ticket> tickets = clientService.getAllTickets();

        Person targetPerson = persons.stream().filter(p -> p.getId() == personId).findFirst().orElseThrow();
        Ticket targetTicket = tickets.stream().filter(t -> t.getId() == ticketId).findFirst().orElseThrow();
        targetPerson.setBalance(targetPerson.getBalance() - targetTicket.getPrice() * (100 - discountPercent) / 100);
        targetTicket.setSold(true);
        targetPerson.getTickets().add(targetTicket);

        Person updatedPerson = clientService.updatePerson(targetPerson);
        return updatedPerson;
    }
}
