package org.javabrains.jax.rs.messenger.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/messages") // All controller methods in this class will have this prefix
public class MessageResource {

    @GET // This is a GET request and will resolve to /messages
    @Produces(MediaType.TEXT_PLAIN) // This is the return type to the HTTP request
    public String getMessages() {
        return "Hello World!";
    }
}
