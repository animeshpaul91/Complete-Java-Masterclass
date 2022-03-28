package io.javabrains.springbootstarter.topic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicController {

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        Topic topic1 = new Topic("Spring", "Spring Framework", "Spring Framework Description");
        Topic topic2 = new Topic("Java", "Core Java", "Core Java Description");
        Topic topic3 = new Topic("Javascript", "Javascript", "Javascript Description");
        return Arrays.asList(topic1, topic2, topic3);
    } // object to JSON serialization and deserialization is done by Jackson
}
