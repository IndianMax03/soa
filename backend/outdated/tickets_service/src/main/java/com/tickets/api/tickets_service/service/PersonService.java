package com.tickets.api.tickets_service.service;

import com.tickets.api.tickets_service.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();

    Person createPerson(Person person);

}
