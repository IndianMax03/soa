package com.tickets.api.tickets_service.service;

import com.tickets.api.tickets_service.entity.Person;
import com.tickets.api.tickets_service.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

}
