package com.tickets.api.tickets_service.repository;

import com.tickets.api.tickets_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
