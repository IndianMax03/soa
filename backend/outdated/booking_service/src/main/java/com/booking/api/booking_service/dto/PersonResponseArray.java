package com.booking.api.booking_service.dto;

import com.booking.api.booking_service.entity.Person;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "PersonResponseArray")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonResponseArray {

    @XmlElementWrapper(name = "persons")
    @XmlElement(name = "person")
    private List<Person> persons;

    public PersonResponseArray() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
