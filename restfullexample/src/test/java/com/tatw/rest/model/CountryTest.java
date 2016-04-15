package com.tatw.rest.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by hans on 15-04-16.
 */
public class CountryTest {

    private Country country;

    @Before
    public void setUp() throws Exception {
        country = new Country();
        assertNotNull(country);
    }

    @Test
    public void testGetCode() throws Exception {
        assertNull(country.getCode());
        country.setCode("CODE");
        assertEquals("CODE", country.getCode());
    }

    @Test
    public void testGetName() throws Exception {
        assertNull(country.getName());
        country.setName("NAME");
        assertEquals("NAME", country.getName());

    }

}