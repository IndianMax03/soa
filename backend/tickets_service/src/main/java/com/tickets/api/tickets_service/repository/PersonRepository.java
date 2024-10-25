package com.tickets.api.tickets_service.repository;

import com.tickets.api.tickets_service.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
