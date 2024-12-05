package com.brigada.tickets_web_service.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/ping")
@Produces("text/plain")
public class PingController {

    @GET
    public String ping() {
        return "pong";
    }

}
