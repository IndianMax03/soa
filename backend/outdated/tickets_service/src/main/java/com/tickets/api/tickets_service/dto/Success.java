package com.tickets.api.tickets_service.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@XmlRootElement(name = "success")
public class Success {

    @Setter
    @XmlElement
    int code;

    @Setter
    @XmlElement
    private String message;

    @XmlElement
    private LocalDateTime time = LocalDateTime.now();

}
