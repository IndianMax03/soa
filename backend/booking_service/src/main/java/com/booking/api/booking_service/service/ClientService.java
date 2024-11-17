package com.booking.api.booking_service.service;

import com.booking.api.booking_service.entity.Person;
import com.booking.api.booking_service.entity.Ticket;

import java.util.List;

public interface ClientService {

    List<Person> getAllPersons();

    List<Ticket> getAllTickets();

    Person updatePerson(Person person);

}
