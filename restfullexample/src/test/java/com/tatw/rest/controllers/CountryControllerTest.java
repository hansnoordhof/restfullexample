package com.tatw.rest.controllers;

import com.tatw.rest.model.Country;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by hans on 15-04-16.
 */
public class CountryControllerTest {

    private CountryController controller;

    @Before
    public void setUp() throws Exception {
        controller = new CountryController();
        assertNotNull(controller);
    }

    @Test
    public void testGetSingleCountry() throws Exception {
        Response response = controller.getSingleCountry();
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        Object object = response.getEntity();
        assertNotNull(object);
        assertTrue(object instanceof Country);
        Country country = (Country)object;
        assertEquals("NL", country.getCode());
        assertEquals("Nederland", country.getName());
    }
}