package org.javabrains.jax.rs.messenger.service;

import org.javabrains.jax.rs.messenger.database.DatabaseClass;
import org.javabrains.jax.rs.messenger.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MessageService {
    private static Map<Long, Message> messages;

    public MessageService() {
        messages = DatabaseClass.getMessages();

        // Adding dummy messages for test
        Message m1 = new Message(1L, "Hello World!", "Animesh");
        Message m2 = new Message(2L, "Hello Jersey!", "Swagat");
        messages.put(m1.getId(), m1);
        messages.put(m2.getId(), m2);
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        long messageId = messages.size() + 1;
        message.setId(messageId);
        setMessageTimestamp(message);
        messages.put(messageId, message);
        return message;
    }

    private void setMessageTimestamp(Message message) {
        message.setCreated(new Date());
    }

    public Message updateMessage(Message message) {
        if (message.getId() < 1) return null;
        messages.put(message.getId(), message);
        return message;
    }

    public Message deleteMessage(long id) {
        return messages.remove(id);
    }
}
