package com.tatw.rest.controllers;

import com.tatw.rest.response.Message;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by hans on 15-04-16.
 */
@Path("helloworld")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldController {

    @GET
    public Message getHelloWorld() {
        Message msg = new Message();
        msg.setMessage("Hello World!!!");
        return msg;
    }

}
