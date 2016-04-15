package com.tatw.rest.controllers;

import com.tatw.rest.config.BeanConfig;
import com.tatw.rest.model.Country;
import com.tatw.rest.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.*;

/**
 * Created by hans on 15-04-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfig.class, loader=AnnotationConfigContextLoader.class)
public class CountryControllerTest {

    @Autowired
    private CountryService countryService;

    private CountryController controller;

    @Before
    public void setUp() throws Exception {
        controller = new CountryController();
        controller.setCountryService(countryService);
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

    @Test
    public void testSearchCountryByCode() {
        Response response = controller.getCountryByCode("NL");
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testSearchCountryByCodeNotFound() {
        Response response = controller.getCountryByCode("NLNL");
        assertNotNull(response);
        assertEquals(404, response.getStatus());
    }

}