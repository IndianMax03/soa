package com.booking.api.booking_service.service;

import com.booking.api.booking_service.dto.PersonResponseArray;
import com.booking.api.booking_service.dto.TicketResponseArray;
import com.booking.api.booking_service.entity.Person;
import com.booking.api.booking_service.entity.Ticket;
import com.booking.api.booking_service.service.util.RequestsHTTPS;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class ClientServiceImpl implements ClientService {
    @Override
    public List<Person> getAllPersons() {
        try (Client client = ClientBuilder.newBuilder().hostnameVerifier((hostname, session) -> true).build()) {

            Response personsResponse = client
                    .target(RequestsHTTPS.ALL_PERSONS.toString())
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .get();

            PersonResponseArray persons = personsResponse.readEntity(PersonResponseArray.class);

            return persons.getPersons();

        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        try (Client client = ClientBuilder.newBuilder().hostnameVerifier((hostname, session) -> true).build()) {

            Response ticketsResponse = client
                    .target(RequestsHTTPS.ALL_TICKETS.toString())
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .get();

            TicketResponseArray tickets = ticketsResponse.readEntity(TicketResponseArray.class);

            return tickets.getTickets();

        }
    }

    @Override
    public Person updatePerson(Person person) {
        try (Client client = ClientBuilder.newBuilder().hostnameVerifier((hostname, session) -> true).build()) {

            Response updatedPersonResponse = client
                    .target(RequestsHTTPS.UPDATE_PERSON.toString())
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.entity(person, MediaType.APPLICATION_XML_TYPE));

            Person updatedPerson = updatedPersonResponse.readEntity(Person.class);
            return updatedPerson;

        }
    }

}
