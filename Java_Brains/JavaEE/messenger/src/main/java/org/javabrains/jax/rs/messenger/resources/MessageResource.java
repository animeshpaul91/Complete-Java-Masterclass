package org.javabrains.jax.rs.messenger.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.javabrains.jax.rs.messenger.model.Message;
import org.javabrains.jax.rs.messenger.service.MessageService;

import java.util.List;

@Path("/messages") // All controller methods in this class will have this prefix
public class MessageResource {

    private final MessageService messageService;

    public MessageResource() {
        this.messageService = new MessageService();
    }

    @GET // This is a GET request and will resolve to /messages
    @Produces(MediaType.APPLICATION_XML) // This is the return type to the HTTP request
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_XML)
    public Message getMessageInstance(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }
}
