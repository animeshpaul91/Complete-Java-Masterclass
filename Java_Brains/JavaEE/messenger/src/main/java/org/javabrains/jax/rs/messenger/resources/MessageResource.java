package org.javabrains.jax.rs.messenger.resources;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.javabrains.jax.rs.messenger.model.Message;
import org.javabrains.jax.rs.messenger.resources.beans.MessageFilterBean;
import org.javabrains.jax.rs.messenger.service.MessageService;

import java.util.List;

@Path("/messages") // All controller methods in this class will have this prefix
@Consumes(MediaType.APPLICATION_JSON) // This represents the body of the request
@Produces(MediaType.APPLICATION_JSON) // This is the return type to the HTTP request
public class MessageResource {

    private final MessageService messageService;

    public MessageResource() {
        this.messageService = new MessageService();
    }

    @GET // This is a GET request and will resolve to /messages
    public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {

        // Even with URIs with query parameters, jersey will call the same controller method
        int year = messageFilterBean.getYear(), start = messageFilterBean.getStart(), size = messageFilterBean.getSize();
        if (year > 0) return messageService.getAllMessagesForYear(year);
        if (start >= 0 && size > 0) return messageService.getAllMessagesPaginated(start, size);
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessageInstance(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
        messageService.deleteMessage(messageId);
    }

    @Path("/{messageId}/comments") // messageId is used in the Subresource
    public CommentResource getCommentResource() {
        return new CommentResource(); // this returns an instance of the subresource
    }
}
