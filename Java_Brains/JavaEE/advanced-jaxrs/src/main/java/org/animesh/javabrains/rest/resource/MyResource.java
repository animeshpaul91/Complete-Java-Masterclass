package org.animesh.javabrains.rest.resource;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Calendar;
import java.util.Date;

@Path("/test")
@Singleton
// this annotation will only create a single instance of MyResource class for every instance of a running application
public class MyResource {

    private int count;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        count++;
        return "It Works! This method was called: " + count + " times";
    }

    @GET()
    @Path("/date")
    @Produces(MediaType.TEXT_PLAIN)
    public Date getDate() {
        return Calendar.getInstance().getTime();
    }
}
