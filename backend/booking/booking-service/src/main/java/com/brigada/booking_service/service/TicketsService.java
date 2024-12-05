package com.brigada.booking_service.service;

import com.brigada.booking_service.client.TicketsClient;
import com.brigada.general.model.dto.PageDto;
import com.brigada.general.model.dto.PersonDto;
import com.brigada.general.model.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketsService {

    private final TicketsClient ticketsClient;

    public List<TicketDto> getAllTickets() {
        int pageNumber = 0;
        int pageSize = 1000;
        List<TicketDto> allTickets = new ArrayList<>();
        PageDto<TicketDto> page;

        do {
            page = ticketsClient.getTickets(pageNumber, pageSize);
            allTickets.addAll(page.getContent());
            pageNumber++;
        } while (pageNumber < page.getMeta().getTotalPages());

        return allTickets;
    }

    public List<PersonDto> getAllPersons() {
        int pageNumber = 0;
        int pageSize = 1000;
        List<PersonDto> allPersons = new ArrayList<>();
        PageDto<PersonDto> page;

        do {
            page = ticketsClient.getPersons(pageNumber, pageSize);
            allPersons.addAll(page.getContent());
            pageNumber++;
        } while (pageNumber < page.getMeta().getTotalPages());

        return allPersons;
    }

    public void updateTicket(Long id, TicketDto updatedTicket) {
        ticketsClient.updateTicket(id, updatedTicket);
    }

    public void updatePerson(Long id, PersonDto updatedPerson) {
        ticketsClient.updatePerson(id, updatedPerson);
    }

}
