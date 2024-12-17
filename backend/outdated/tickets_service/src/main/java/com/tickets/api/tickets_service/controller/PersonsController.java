package com.tickets.api.tickets_service.controller;

import com.tickets.api.tickets_service.dto.PersonResponseArray;
import com.tickets.api.tickets_service.entity.Person;
import com.tickets.api.tickets_service.repository.PersonRepository;
import com.tickets.api.tickets_service.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/persons", produces = MediaType.APPLICATION_XML_VALUE)
@Slf4j
@CrossOrigin(origins = "*")
public class PersonsController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public PersonResponseArray getPersons(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Double balance,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "500") Integer pageSize,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {


        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(_sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sort[0]));
        }
        List<Person> persons;
        Pageable pagingSort = PageRequest.of(page, pageSize, Sort.by(orders));
        Page<Person> personPages;

        if (id != null || username != null || password != null || balance != null)
            personPages = personRepository.findByIdOrUsernameOrPasswordOrBalance(id, username, password, balance, pagingSort);
        else
            personPages = personRepository.findAll(pagingSort);

        persons = personPages.getContent();

        PersonResponseArray response = new PersonResponseArray();
        response.setPersons(persons);
        response.setTotalElements(personPages.getTotalElements());
        response.setTotalPages(personPages.getTotalPages());
        return response;
    }

    @PostMapping
    public Person createPersons(@RequestBody Person person) {
        if (person.getId() == 0) person.setBalance(10000);
        return personService.createPerson(person);
    }

}
