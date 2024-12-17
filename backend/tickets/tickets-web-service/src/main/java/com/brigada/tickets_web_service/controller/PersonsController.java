package com.brigada.tickets_web_service.controller;

import com.brigada.general.model.dto.PageDto;
import com.brigada.tickets_ejb.model.Person;
import com.brigada.tickets_web_service.service.PersonsService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonsController {

    @Inject
    private PersonsService personsService;

    @GET
    public Response getPersons(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sort") List<String> sortList,
            @QueryParam("id") String idValue,
            @QueryParam("id-filter") String idFilter,
            @QueryParam("username") String usernameValue,
            @QueryParam("username-filter") String usernameFilter,
            @QueryParam("password") String passwordValue,
            @QueryParam("password-filter") String passwordFilter,
            @QueryParam("balance") String balanceValue,
            @QueryParam("balance-filter") String balanceFilter
    ) {
        PageDto<Person> persons = personsService.findAll(
                page, size, sortList,
                idValue, idFilter,
                usernameValue, usernameFilter,
                passwordValue, passwordFilter,
                balanceValue, balanceFilter
        );
        return Response.ok(persons).build();
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") Long id) {
        Person person = personsService.findById(id);
        return Response.ok(person).build();
    }

    @POST
    public Response createPerson(@Valid Person person) {
        personsService.save(person);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") Long id, @Valid Person person) {
        personsService.update(id, person);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        personsService.delete(id);
        return Response.ok().build();
    }

}
