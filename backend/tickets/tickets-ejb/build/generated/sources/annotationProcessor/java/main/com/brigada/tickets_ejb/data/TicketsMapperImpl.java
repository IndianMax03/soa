package com.brigada.tickets_ejb.data;

import com.brigada.tickets_ejb.model.Ticket;
import jakarta.enterprise.context.ApplicationScoped;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T22:11:06+0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 20.0.1 (Oracle Corporation)"
)
@ApplicationScoped
public class TicketsMapperImpl implements TicketsMapper {

    @Override
    public Ticket updateFields(Ticket existingTicket, Ticket newTicket) {
        if ( newTicket == null ) {
            return existingTicket;
        }

        existingTicket.setName( newTicket.getName() );
        existingTicket.setCoordinates( newTicket.getCoordinates() );
        existingTicket.setCreationDate( newTicket.getCreationDate() );
        existingTicket.setSold( newTicket.isSold() );
        existingTicket.setPrice( newTicket.getPrice() );
        existingTicket.setType( newTicket.getType() );
        existingTicket.setVenue( newTicket.getVenue() );
        existingTicket.setOwner( newTicket.getOwner() );

        return existingTicket;
    }
}
