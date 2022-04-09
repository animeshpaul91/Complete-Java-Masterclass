package org.javabrains.jax.rs.messenger.service;

import org.javabrains.jax.rs.messenger.model.Message;

import java.util.Arrays;
import java.util.List;

public class MessageService {
    public List<Message> getAllMessages() {
        Message m1 = new Message(1L, "Hello World!", "Animesh");
        Message m2 = new Message(2L, "Hello Jersey!", "Koushik");
        Message m3 = new Message(3L, "Hello Servlets!", "Swagat");
        return Arrays.asList(m1, m2, m3);
    }
}
