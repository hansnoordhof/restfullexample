package com.tatw.rest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by hans on 14-04-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class CountryServiceContextTest {

    @Configuration
    static class ContextConfiguration {

        // this bean will be injected into the CountryService class
        @Bean
        public CountryService countryService() {
            CountryService countryService = new CountryService();
            return countryService;
        }
    }

    @Autowired
    private CountryService countryService;

    @Test
    public void testConstructor() {
        assertNotNull(countryService);
        assertTrue(countryService.getCountryList().size() == 243);
    }

}