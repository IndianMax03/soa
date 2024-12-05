package com.tickets.api.tickets_service.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "sum")
public class Sum {

    @XmlElement
    double sum;

}
