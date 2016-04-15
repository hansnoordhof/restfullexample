package com.tatw.rest.controllers;

import com.tatw.rest.response.Message;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by hans on 15-04-16.
 */
public class HelloWorldControllerTest {

    private HelloWorldController controller;

    @Before
    public void setUp() throws Exception {
        controller = new HelloWorldController();
        assertNotNull(controller);
    }

    @Test
    public void testGetHelloWorld() throws Exception {
        Message response = controller.getHelloWorld();
        assertNotNull(response);
        assertEquals("Hello World!!!", response.getMessage());
    }
}