package com.tatw.rest.controllers;

import com.tatw.rest.model.Country;
import com.tatw.rest.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hans on 15-04-16.
 */
@Path("country")
@Produces(MediaType.APPLICATION_JSON)
public class CountryController {

    @Autowired
    private CountryService countryService;

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

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

    @GET
    @Path("{code}")
    public Response getCountryByCode(@NotNull @PathParam("code") String code) {
        Country country = countryService.findByCode(code);
        if (country != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(country)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    public Response addCountryToList(@Valid @NotNull Country country) {
        Country countryFound = countryService.findByCode(country.getCode());
        if (countryFound == null) {
            countryService.getCountryList().add(country);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } else {
            return Response
                    .status(Response.Status.CONFLICT)
                    .build();
        }
    }

    @POST
    public Response changeCountry(@Valid @NotNull Country country) {
        Country countryFound = countryService.findByCode(country.getCode());
        if (countryFound != null) {
            countryFound = countryService.replaceByCode(country);
            return Response
                    .status(Response.Status.OK)
                    .entity(countryFound)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

}
