package com.tatw.rest;

import com.tatw.rest.config.BeanConfig;
import com.tatw.rest.controllers.CountryController;
import com.tatw.rest.controllers.HelloWorldController;
import com.tatw.rest.model.Country;
import com.tatw.rest.response.Message;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.filter.RequestContextFilter;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by hans on 15-04-16.
 */
public class ApplicationEndPointTest extends JerseyTest {

    public ApplicationEndPointTest()
    {
        super(new InMemoryTestContainerFactory());
    }

    @Override
    protected Application configure() {
        ResourceConfig rc = new ResourceConfig();
        rc.property("contextConfig", new AnnotationConfigApplicationContext(BeanConfig.class));
        rc.register(SpringLifecycleListener.class).register(RequestContextFilter.class);
        rc.registerClasses(HelloWorldController.class);
        rc.registerClasses(CountryController.class);
        return rc;
    }

    @Test
    public void testHelloWorld() {
        Message response = target("helloworld").request().get(Message.class);
        assertEquals("Hello World!!!", response.getMessage());
    }

    @Test
    public void testHelloWorld404() {
        Response response = target("helloworld/doesnotexists").request().get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testSingleCountry(){
        Response response = target("country").request().get();
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        Object object = response.readEntity(Country.class);
        assertTrue(object instanceof Country);
        Country country = (Country)object;
        assertEquals("NL", country.getCode());
        assertEquals("Nederland", country.getName());
    }

    @Test
    public void testSingleCountry404() {
        Response response = target("country/doesnotexists").request().get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testGetCountryByCode() {
        Response response = target("country/AU").request().get();
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        Object object = response.readEntity(Country.class);
        assertTrue(object instanceof Country);
        Country country = (Country)object;
        assertEquals("AU", country.getCode());
        assertEquals("Australia", country.getName());

    }


}
