package com.tickets.api.tickets_service.dto;

import com.tickets.api.tickets_service.entity.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "TicketResponseArray")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor

public class TicketResponseArray {

    @XmlElementWrapper(name = "tickets")
    @XmlElement(name = "ticket")
    private List<Ticket> tickets;

}
