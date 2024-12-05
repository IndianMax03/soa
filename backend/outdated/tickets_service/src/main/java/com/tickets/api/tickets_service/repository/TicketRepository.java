package com.tickets.api.tickets_service.repository;

import com.tickets.api.tickets_service.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Page<Ticket> findByIdOrNameOrIsSoldOrPrice(Long id, String name, Boolean isSold, Double price, Pageable pageable);

}
