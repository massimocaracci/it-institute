package com.pantasoft.itinstitute.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

/**
 * Created by massimocaracci on 10/05/2017.
 */

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public ResponseEntity getAllTopics()  {
        List<Topic> topics = topicService.getAllTopic();
        if (topics.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(topics);
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity getTopicById(@PathVariable String id){

         Optional<Topic> topicOptional= topicService.getTopic(id);

         if(topicOptional.isPresent())
             return  ResponseEntity.status(HttpStatus.OK).body(topicOptional.get());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping(value = "topic")
    public ResponseEntity addTopic(@RequestBody Topic topic){
        return  ResponseEntity.status(HttpStatus.CREATED).body(topicService.addTopic(topic));
    }

    @PutMapping(value = "/topic")
    public ResponseEntity updateTopic(@RequestBody Topic topic){
        Topic t = topicService.updateTopic(topic);
        return  ResponseEntity.status(HttpStatus.OK).body(t);
    }

    @DeleteMapping(value = "topic/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable String id){
        topicService.deleteTopic(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
