package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.dto.Sum;
import com.tickets.api.tickets_service.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tickets/price", produces = MediaType.APPLICATION_XML_VALUE)
@Slf4j
@CrossOrigin(origins = "*")
public class TicketsPriceController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/sum")
    public Sum getTicketsPriceSum() {
        double result = ticketService.getPriceSum();
        Sum response = new Sum();
        response.setSum(result);
        return response;
    }

}
