package com.booking.api.booking_service.service.util;

public enum RequestsHTTPS {

    ALL_PERSONS("https://tickets-service:8082/tickets_service/persons"),
    ALL_TICKETS("https://tickets-service:8082/tickets_service/tickets"),
    UPDATE_PERSON("https://tickets-service:8082/tickets_service/persons");

    private final String url;

    RequestsHTTPS(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
