package com.brigada.booking_service.controller;

import com.brigada.booking_service.service.BookingService;
import com.brigada.general.model.soap.SellRequest;
import com.brigada.general.model.soap.SellWithDiscountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SellController {

    private final String NAMESPACE_URI = "http://www.brigada.com/general/model/soap";

    private final BookingService bookingService;

    @Autowired
    public SellController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sellRequest")
    @ResponsePayload
    public SellRequest sell(
            @RequestPayload SellRequest request
    ) {
        return bookingService.sellTicketToPerson(request.getTicketId(), request.getPersonId(), request.getPrice());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sellWithDiscountRequest")
    @ResponsePayload
    public SellWithDiscountRequest sellWithDiscount(
            @RequestPayload SellWithDiscountRequest request
    ) {
        return bookingService.sellTicketToPersonWithDiscount(request.getTicketId(), request.getPersonId(), request.getDiscount());
    }

}
