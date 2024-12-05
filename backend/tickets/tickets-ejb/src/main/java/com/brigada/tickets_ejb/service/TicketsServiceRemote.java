package com.brigada.tickets_ejb.service;

import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.model.Ticket;
import com.brigada.tickets_ejb.model.Venue;
import jakarta.ejb.Remote;
import jakarta.transaction.Transactional;

import java.util.List;

@Remote
public interface TicketsServiceRemote {

    PageDto<Ticket> findAll(
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
    );

    Ticket findById(Long id);

    @Transactional
    void save(Ticket ticket);

    @Transactional
    void update(Long id, Ticket ticket);

    @Transactional
    void delete(Long id);

    Double getTicketsPriceSum();

    Venue getTicketsVenueMin();

    List<Venue> getTicketsUniqueVenues();

}
