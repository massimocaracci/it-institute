package com.pantasoft.itinstitute.topic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pantasoft.itinstitute.*;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getTopics_NO_CONTENT() {
        ResponseEntity<List> response = this.restTemplate.getForEntity(
                "/topics", List.class, "");
        Assert.assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

    @Test
    public void addTopic() {
        Topic topic = new Topic("javascript","Javascript","Javascript Description");

        ResponseEntity<Topic> response =  this.restTemplate.postForEntity(
                "/topic", topic, null);

        Assert.assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
    }


    @Test
    public void getTopicsByIdTest() {
        String idTopic = "javascript";
        ResponseEntity<Topic> response = this.restTemplate.getForEntity(
                "/topic/"+idTopic, Topic.class, "");

        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void getTopics_OK() {
        ResponseEntity<List> response = this.restTemplate.getForEntity(
                "/topics", List.class, "");
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void updateTopic() {

        String topicName = "Javascript updated";
        String topicId = "javascript";
        String topicDescription = "Javascript Description Updated";

        Topic topic = new Topic(topicId, topicName, topicDescription);

        ResponseEntity <Topic> response =
                restTemplate.exchange("/topic", HttpMethod.PUT, new HttpEntity<>(topic),
                        Topic.class);

        System.out.println("running updateTopic...");

       assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Topic topicBody = response.getBody();


        assertThat(topicBody.getName(), is(topicName));
    }

}