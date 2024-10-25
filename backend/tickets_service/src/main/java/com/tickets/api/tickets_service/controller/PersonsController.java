package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.dto.PersonResponseArray;
import com.tickets.api.tickets_service.entity.Person;
import com.tickets.api.tickets_service.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/persons", produces = MediaType.APPLICATION_XML_VALUE)
@Slf4j
@CrossOrigin(origins = "*")
public class PersonsController {

    @Autowired
    PersonService personService;

    @GetMapping
    public PersonResponseArray getPersons() {
        List<Person> result = personService.getPersons();
        PersonResponseArray response = new PersonResponseArray();
        response.setPersons(result);
        return response;
    }

    @PostMapping
    public Person createPersons(@RequestBody Person person) {
        return personService.createPerson(person);
    }

}
