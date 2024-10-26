package com.tickets.api.tickets_service.dto;

import com.tickets.api.tickets_service.entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "PersonResponseArray")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
public class PersonResponseArray {

    @XmlElementWrapper(name = "persons")
    @XmlElement(name = "person")
    private List<Person> persons;

    @XmlElement
    private int totalPages;

    @XmlElement
    private long totalElements;

}
