package com.tickets.api.tickets_service.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ping", produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "*")
public class PingController {

    @GetMapping
    public String ping() {
        return "pong";
    }

}
