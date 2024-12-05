package com.tickets.api.tickets_service.repository;

import com.tickets.api.tickets_service.entity.Person;
import com.tickets.api.tickets_service.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByIdOrUsernameOrPasswordOrBalance(Long id, String username, String password, Double balance, Pageable pageable);

}
