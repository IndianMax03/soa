package com.brigada.tickets_ejb.data;

import com.brigada.tickets_ejb.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T23:13:28+0300",
    comments = "version: 1.6.0, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 20.0.1 (Oracle Corporation)"
)
@ApplicationScoped
public class PersonsMapperImpl implements PersonsMapper {

    @Override
    public Person updateFields(Person existingPerson, Person newPerson) {
        if ( newPerson == null ) {
            return existingPerson;
        }

        existingPerson.setUsername( newPerson.getUsername() );
        existingPerson.setPassword( newPerson.getPassword() );
        existingPerson.setBalance( newPerson.getBalance() );

        return existingPerson;
    }
}
