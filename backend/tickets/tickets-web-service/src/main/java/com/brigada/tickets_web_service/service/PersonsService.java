package com.brigada.tickets_web_service.service;

import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.model.Person;
import com.brigada.tickets_ejb.service.PersonsServiceRemote;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@ApplicationScoped
public class PersonsService {

    private final PersonsServiceRemote personsServiceRemote = getFromEJBPool("ejb:/tickets-ejb/PersonsService!com.brigada.tickets_ejb.service.PersonsServiceRemote");

    public PersonsService() throws NamingException {
    }

    public PageDto<Person> findAll(
            int page, int size, List<String> sortList,
            String idValue, String idFilter,
            String usernameValue, String usernameFilter,
            String passwordValue, String passwordFilter,
            String balanceValue, String balanceFilter
    ) {

        return personsServiceRemote.findAll(
                page, size, sortList,
                idValue, idFilter,
                usernameValue, usernameFilter,
                passwordValue, passwordFilter,
                balanceValue, balanceFilter
        );
    }

    public Person findById(Long id) {
        return personsServiceRemote.findById(id);
    }

    @Transactional
    public void save(Person person) {
        personsServiceRemote.save(person);
    }

    @Transactional
    public void update(Long id, Person person) {
        personsServiceRemote.update(id, person);
    }

    @Transactional
    public void delete(Long id) {
        personsServiceRemote.delete(id);
    }


    private PersonsServiceRemote getFromEJBPool(String name) throws NamingException {
        return (PersonsServiceRemote) new InitialContext().lookup(name);
    }

}
