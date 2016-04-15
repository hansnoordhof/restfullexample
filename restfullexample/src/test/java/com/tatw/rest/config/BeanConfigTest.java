package com.tatw.rest.config;

import com.tatw.rest.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static junit.framework.TestCase.*;

/**
 * Created by hans on 15-04-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfig.class, loader=AnnotationConfigContextLoader.class)
public class BeanConfigTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testBeanConfig() throws Exception {
        assertNotNull(countryService);
        assertTrue(countryService.getCountryList().size() == 243);
        assertNotNull(countryService.findByCode("AU"));
        assertNull(countryService.findByCode("AUAU"));
    }

}