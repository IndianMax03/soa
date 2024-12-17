package com.brigada.booking_service.client;

import com.brigada.booking_service.config.RibbonClientConfig;
import com.brigada.general.model.dto.PageDto;
import com.brigada.general.model.dto.PersonDto;
import com.brigada.general.model.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RibbonClient(name = "tickets-service", configuration = RibbonClientConfig.class)
public class TicketsClient {

    private final RestTemplate restTemplate;

    @Autowired
    public TicketsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PageDto<PersonDto> getPersons(int page, int size) {
        return restTemplate.exchange(
                "https://tickets-service:8443/tickets-service/persons?page={page}&size={size}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDto<PersonDto>>() {
                },
                page,
                size
        ).getBody();
    }

    public PageDto<TicketDto> getTickets(int page, int size) {
        return restTemplate.exchange(
                "https://tickets-service:8443/tickets-service/tickets?page={page}&size={size}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDto<TicketDto>>() {
                },
                page,
                size
        ).getBody();
    }

    public void updatePerson(Long id, PersonDto personDto) {
        restTemplate.put(
                "https://tickets-service:8443/tickets-service/persons/{id}",
                personDto,
                id
        );
    }

    public void updateTicket(Long id, TicketDto ticketDto) {
        restTemplate.put(
                "https://tickets-service:8443/tickets-service/tickets/{id}",
                ticketDto,
                id
        );
    }

}
