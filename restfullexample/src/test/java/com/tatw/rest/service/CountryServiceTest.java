package com.tatw.rest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static junit.framework.TestCase.*;

/**
 * Created by hans on 14-04-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CountryService.class, loader=AnnotationConfigContextLoader.class)
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testConstructor() {
        assertNotNull(countryService);
        countryService.init();
        assertTrue(countryService.getCountryList().size() == 243);
        assertNotNull(countryService.findByCode("NL"));
        assertNull(countryService.findByCode("NLNL"));
    }


}