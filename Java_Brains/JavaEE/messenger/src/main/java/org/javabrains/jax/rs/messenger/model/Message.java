package org.javabrains.jax.rs.messenger.model;

import java.util.Date;

@jakarta.xml.bind.annotation.XmlRootElement
public class Message {
    private long id;
    private String message;
    private Date created;
    private String author;

    public Message() { // used by Jackson
    }

    public Message(long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
