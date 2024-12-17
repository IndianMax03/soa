package com.brigada.tickets_ejb.data;

import com.brigada.tickets_ejb.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface TicketsMapper {

    @Mapping(target = "id", ignore = true)
    Ticket updateFields(@MappingTarget Ticket existingTicket, Ticket newTicket);

}
