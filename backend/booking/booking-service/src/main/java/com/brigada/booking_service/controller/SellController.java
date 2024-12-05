package com.brigada.booking_service.controller;

import com.brigada.booking_service.service.BookingService;
import com.brigada.booking_service.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellController {

    BookingService bookingService;

    @Autowired
    public SellController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{ticket-id}/{person-id}/{price}")
    public ResponseEntity<String> sell(
            @PathVariable("ticket-id") Long ticketId,
            @PathVariable("person-id") Long personId,
            @PathVariable("price") Double price
    ) {
        return bookingService.sellTicketToPerson(ticketId, personId, price, null);
    }

    @PostMapping("/discount/{ticket-id}/{person-id}/{discount}")
    public ResponseEntity<String> sellWithDiscount(
            @PathVariable("ticket-id") Long ticketId,
            @PathVariable("person-id") Long personId,
            @PathVariable("discount") Integer discountPercent
    ) {
        return bookingService.sellTicketToPerson(ticketId, personId, null, discountPercent);
    }

}
