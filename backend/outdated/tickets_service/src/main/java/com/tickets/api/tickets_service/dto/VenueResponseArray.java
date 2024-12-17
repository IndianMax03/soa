package com.tickets.api.tickets_service.dto;

import com.tickets.api.tickets_service.entity.Venue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "VenueResponseArray")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
public class VenueResponseArray {

    @XmlElementWrapper(name = "venues")
    @XmlElement(name = "venue")
    private Set<Venue> venues;

}
