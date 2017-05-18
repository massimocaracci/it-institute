package com.pantasoft.itinstitute.topic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

/**
 * Created by massimocaracci on 10/05/2017.
 */

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public  List<Topic> getAllTopic(){
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);

        return topics;
    }

    public Optional <Topic> getTopic(String id) {

        return Optional.ofNullable( topicRepository.findOne(id) );
    }

    public Topic addTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public ResponseEntity deleteTopic(String id) {
        topicRepository.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
