package com.booking.api.booking_service.service;

import com.booking.api.booking_service.entity.Person;

public interface TicketWindowService {

    Person sellTicketToPerson(Integer ticketId, Integer personId, Double price);

    Person sellTicketToPersonWithDiscount(Integer ticketId, Integer personId, Integer discountPercent);

}
