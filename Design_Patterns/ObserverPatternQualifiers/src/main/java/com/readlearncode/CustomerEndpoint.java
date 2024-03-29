package com.readlearncode;


import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import static com.readlearncode.CustomerEvent.Type.ADD;
import static com.readlearncode.CustomerEvent.Type.REMOVE;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
@Path("/customers")
public class CustomerEndpoint {

    @Inject
    @CustomerEvent(ADD) // annotation defined in CustomerEvent Interface
    private Event<Customer> customerAddEvent;

    @Inject
    @CustomerEvent(REMOVE) // annotation defined in CustomerEvent Interface
    private Event<Customer> customerRemoveEvent;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void newCustomer(Customer customer) {
        customerAddEvent.fire(customer);
        // all services (AuthenticationService, CustomerService & EmailServices) will be triggered because they are observers to this event.
        // in a decoupled manner
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeCustomer(Customer customer) {
        customerRemoveEvent.fire(customer);
    }

}