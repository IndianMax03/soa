package com.brigada.tickets_ejb.service;


import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.data.TicketsMapper;
import com.brigada.tickets_ejb.filter.FilterCriterion;
import com.brigada.tickets_ejb.model.Ticket;
import com.brigada.tickets_ejb.repository.TicketsRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Stateless
public class TicketsService implements TicketsServiceRemote {

    @Inject
    private TicketsRepository ticketsRepository;

    @Inject
    private TicketsMapper mapper;

    @Override
    public PageDto<Ticket> findAll(int page, int size, List<String> sortList, String idValue, String idFilter, String nameValue, String nameFilter, String coordinatesXValue, String coordinatesXFilter, String coordinatesYValue, String coordinatesYFilter, String creationDateValue, String creationDateFilter, String isSoldValue, String isSoldFilter, String priceValue, String priceFilter, String typeValue, String typeFilter, String venueIdValue, String venueIdFilter, String venueNameValue, String venueNameFilter, String venueCapacityValue, String venueCapacityFilter, String venueTypeValue, String venueTypeFilter, String venueAddressIdValue, String venueAddressIdFilter, String venueAddressZipCodeValue, String venueAddressZipCodeFilter) {

        List<FilterCriterion> filters = createFilters(idValue, idFilter, nameValue, nameFilter, coordinatesXValue, coordinatesXFilter, coordinatesYValue, coordinatesYFilter, creationDateValue, creationDateFilter, isSoldValue, isSoldFilter, priceValue, priceFilter, typeValue, typeFilter, venueIdValue, venueIdFilter, venueNameValue, venueNameFilter, venueCapacityValue, venueCapacityFilter, venueTypeValue, venueTypeFilter, venueAddressIdValue, venueAddressIdFilter, venueAddressZipCodeValue, venueAddressZipCodeFilter);
        return ticketsRepository.findAll(page, size, sortList, filters);

    }

    @Override
    public Ticket findById(Long id) {
        return ticketsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ticket with id = " + id + " not found"));
    }

    @Override
    public void save(Ticket ticket) {
        ticketsRepository.save(ticket);
    }

    @Override
    public void update(Long id, Ticket ticket) {
        Ticket existingTicket = findById(id);
        Ticket updatedTicket = mapper.updateFields(existingTicket, ticket);
        ticketsRepository.save(updatedTicket);
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = findById(id);
        ticketsRepository.delete(ticket);
    }

    private List<FilterCriterion> createFilters(String idValue, String idFilter, String nameValue, String nameFilter, String coordinatesXValue, String coordinatesXFilter, String coordinatesYValue, String coordinatesYFilter, String creationDateValue, String creationDateFilter, String isSoldValue, String isSoldFilter, String priceValue, String priceFilter, String typeValue, String typeFilter, String venueIdValue, String venueIdFilter, String venueNameValue, String venueNameFilter, String venueCapacityValue, String venueCapacityFilter, String venueTypeValue, String venueTypeFilter, String venueAddressIdValue, String venueAddressIdFilter, String venueAddressZipCodeValue, String venueAddressZipCodeFilter) {
        List<FilterCriterion> filters = new ArrayList<>();

        if (idValue != null && idFilter != null) {
            filters.add(new FilterCriterion("id", idFilter, idValue));
        }
        if (nameValue != null && nameFilter != null) {
            filters.add(new FilterCriterion("name", nameFilter, nameValue));
        }
        if (coordinatesXValue != null && coordinatesXFilter != null) {
            filters.add(new FilterCriterion("coordinates.x", coordinatesXFilter, coordinatesXValue));
        }
        if (coordinatesYValue != null && coordinatesYFilter != null) {
            filters.add(new FilterCriterion("coordinates.y", coordinatesYFilter, coordinatesYValue));
        }
        if (creationDateValue != null && creationDateFilter != null) {
            filters.add(new FilterCriterion("creation_date", creationDateFilter, creationDateValue));
        }
        if (isSoldValue != null && isSoldFilter != null) {
            filters.add(new FilterCriterion("is_sold", isSoldFilter, isSoldValue));
        }
        if (priceValue != null && priceFilter != null) {
            filters.add(new FilterCriterion("price", priceFilter, priceValue));
        }
        if (typeValue != null && typeFilter != null) {
            filters.add(new FilterCriterion("type", typeFilter, typeValue));
        }
        if (venueIdValue != null && venueIdFilter != null) {
            filters.add(new FilterCriterion("venue.id", venueIdFilter, venueIdValue));
        }
        if (venueNameValue != null && venueNameFilter != null) {
            filters.add(new FilterCriterion("venue.name", venueNameFilter, venueNameValue));
        }
        if (venueCapacityValue != null && venueCapacityFilter != null) {
            filters.add(new FilterCriterion("venue.capacity", venueCapacityFilter, venueCapacityValue));
        }
        if (venueTypeValue != null && venueTypeFilter != null) {
            filters.add(new FilterCriterion("venue.type", venueTypeFilter, venueTypeValue));
        }
        if (venueAddressIdValue != null && venueAddressIdFilter != null) {
            filters.add(new FilterCriterion("venue.address.id", venueAddressIdFilter, venueAddressIdValue));
        }
        if (venueAddressZipCodeValue != null && venueAddressZipCodeFilter != null) {
            filters.add(new FilterCriterion("venue.address.zip_code", venueAddressZipCodeFilter, venueAddressZipCodeValue));
        }

        return filters;
    }

}
