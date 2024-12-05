package com.brigada.tickets_ejb.service;

import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.model.Person;
import jakarta.ejb.Remote;
import jakarta.transaction.Transactional;

import java.util.List;

@Remote
public interface PersonsServiceRemote {

    PageDto<Person> findAll(
            int page, int size, List<String> sortList,
            String idValue, String idFilter,
            String usernameValue, String usernameFilter,
            String passwordValue, String passwordFilter,
            String balanceValue, String balanceFilter
    );

    Person findById(Long id);

    @Transactional
    void save(Person person);

    @Transactional
    void update(Long id, Person person);

    @Transactional
    void delete(Long id);

}
