package com.brigada.tickets_ejb.data;

import com.brigada.tickets_ejb.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface PersonsMapper {

    @Mapping(target = "id", ignore = true)
    Person updateFields(@MappingTarget Person existingPerson, Person newPerson);

}
