package com.brigada.booking_service_rest.controller;

import com.brigada.booking_service_rest.service.BookingService;
import com.brigada.general.model.soap.SellRequest;
import com.brigada.general.model.soap.SellWithDiscountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellController {


    private final BookingService bookingService;

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
        return ResponseEntity.ok().body(bookingService.sell(new SellRequest(ticketId, personId, price, null)).getResult());
    }

    @PostMapping("/discount/{ticket-id}/{person-id}/{discount}")
    public ResponseEntity<String> sellWithDiscount(
            @PathVariable("ticket-id") Long ticketId,
            @PathVariable("person-id") Long personId,
            @PathVariable("discount") Integer discountPercent
    ) {
        return ResponseEntity.ok().body(bookingService.sellWithDiscount(new SellWithDiscountRequest(ticketId, personId, discountPercent, null)).getResult());
    }

}
