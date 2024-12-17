package com.brigada.tickets_ejb.service;

import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.data.PersonsMapper;
import com.brigada.tickets_ejb.filter.FilterCriterion;
import com.brigada.tickets_ejb.model.Person;
import com.brigada.tickets_ejb.repository.PersonsRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Stateless
public class PersonsService implements PersonsServiceRemote {

    @Inject
    private PersonsRepository personsRepository;

    @Inject
    private PersonsMapper mapper;

    @Override
    public PageDto<Person> findAll(int page, int size, List<String> sortList, String idValue, String idFilter, String usernameValue, String usernameFilter, String passwordValue, String passwordFilter, String balanceValue, String balanceFilter) {
        List<FilterCriterion> filters = createFilters(idValue, idFilter, usernameValue, usernameFilter, passwordValue, passwordFilter, balanceValue, balanceFilter);
        return personsRepository.findAll(page, size, sortList, filters);
    }

    @Override
    public Person findById(Long id) {
        return personsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Person with id = " + id + " not found"));
    }

    @Override
    public void save(Person person) {
        personsRepository.save(person);
    }

    @Override
    public void update(Long id, Person person) {
        Person existingPerson = findById(id);
        Person updatedPerson = mapper.updateFields(existingPerson, person);
        personsRepository.save(updatedPerson);
    }

    @Override
    public void delete(Long id) {
        Person person = findById(id);
        personsRepository.delete(person);
    }

    private List<FilterCriterion> createFilters(String idValue, String idFilter, String usernameValue, String usernameFilter, String passwordValue, String passwordFilter, String balanceValue, String balanceFilter) {
        List<FilterCriterion> filters = new ArrayList<>();

        if (idValue != null && idFilter != null) {
            filters.add(new FilterCriterion("id", idFilter, idValue));
        }
        if (usernameValue != null && usernameFilter != null) {
            filters.add(new FilterCriterion("username", usernameFilter, usernameValue));
        }
        if (passwordValue != null && passwordFilter != null) {
            filters.add(new FilterCriterion("password", passwordFilter, passwordValue));
        }
        if (balanceValue != null && balanceFilter != null) {
            filters.add(new FilterCriterion("balance", balanceFilter, balanceValue));
        }
        return filters;
    }

}
