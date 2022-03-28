package io.javabrains.springbootstarter.topic;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service // marks this class a Spring Service
public class TopicService {

    private final List<Topic> topics;

    public TopicService() {
        this.topics = Arrays.asList(
                new Topic("Spring", "Spring Framework", "Spring Framework Description"),
                new Topic("Java", "Core Java", "Core Java Description"),
                new Topic("Javascript", "Javascript", "Javascript Description")
        );
    }

    public List<Topic> getAllTopics() {
        return topics;
    }
}
