package com.brigada.general.model.dto;

import com.brigada.general.model.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto implements Serializable {
    private static final long serialVersionUID = 4L;

    private long id;
    private String name;
    private CoordinatesDto coordinates;
    private java.time.LocalDateTime creationDate;
    private boolean isSold;
    private double price;
    private TicketType type;
    private VenueDto venue;
    private PersonDto owner;

}
