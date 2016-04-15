package com.tatw.rest.controllers;

import com.tatw.rest.model.Country;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hans on 15-04-16.
 */
@Path("country")
@Produces(MediaType.APPLICATION_JSON)
public class CountryController {

    @GET
    public Response getSingleCountry() {

        Country country = new Country();
        country.setName("Nederland");
        country.setCode("NL");
        return Response
                .status(Response.Status.OK)
                .entity(country)
                .build();

    }

}
