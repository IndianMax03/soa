package com.booking.api.booking_service.dto;

import com.booking.api.booking_service.entity.Ticket;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "TicketResponseArray")
@XmlAccessorType(XmlAccessType.FIELD)

public class TicketResponseArray {

    @XmlElementWrapper(name = "tickets")
    @XmlElement(name = "ticket")
    private List<Ticket> tickets;

    public TicketResponseArray() {
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
