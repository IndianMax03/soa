package com.brigada.tickets_web_service.service;

import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.model.Ticket;
import com.brigada.tickets_ejb.service.TicketsServiceRemote;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@ApplicationScoped
public class TicketsService {

    private final TicketsServiceRemote ticketsServiceRemote = getFromEJBPool("ejb:/tickets-ejb/TicketsService!com.brigada.tickets_ejb.service.TicketsServiceRemote");

    public TicketsService() throws NamingException {
    }

    public PageDto<Ticket> findAll(
            int page, int size, List<String> sortList,
            String idValue, String idFilter,
            String nameValue, String nameFilter,
            String coordinatesXValue, String coordinatesXFilter,
            String coordinatesYValue, String coordinatesYFilter,
            String creationDateValue, String creationDateFilter,
            String isSoldValue, String isSoldFilter,
            String priceValue, String priceFilter,
            String typeValue, String typeFilter,
            String venueIdValue, String venueIdFilter,
            String venueNameValue, String venueNameFilter,
            String venueCapacityValue, String venueCapacityFilter,
            String venueTypeValue, String venueTypeFilter,
            String venueAddressIdValue, String venueAddressIdFilter,
            String venueAddressZipCodeValue, String venueAddressZipCodeFilter
    ) {

        return ticketsServiceRemote.findAll(
                page, size, sortList,
                idValue, idFilter,
                nameValue, nameFilter,
                coordinatesXValue, coordinatesXFilter,
                coordinatesYValue, coordinatesYFilter,
                creationDateValue, creationDateFilter,
                isSoldValue, isSoldFilter,
                priceValue, priceFilter,
                typeValue, typeFilter,
                venueIdValue, venueIdFilter,
                venueNameValue, venueNameFilter,
                venueCapacityValue, venueCapacityFilter,
                venueTypeValue, venueTypeFilter,
                venueAddressIdValue, venueAddressIdFilter,
                venueAddressZipCodeValue, venueAddressZipCodeFilter
        );
    }

    public Ticket findById(Long id) {
        return ticketsServiceRemote.findById(id);
    }

    @Transactional
    public void save(Ticket ticket) {
        ticketsServiceRemote.save(ticket);
    }

    @Transactional
    public void update(Long id, Ticket ticket) {
        ticketsServiceRemote.update(id, ticket);
    }

    @Transactional
    public void delete(Long id) {
        ticketsServiceRemote.delete(id);
    }


    private TicketsServiceRemote getFromEJBPool(String name) throws NamingException {
        return (TicketsServiceRemote) new InitialContext().lookup(name);
    }

}
