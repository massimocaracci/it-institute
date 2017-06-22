package com.pantasoft.itinstitute.course;

import com.pantasoft.itinstitute.topic.Topic;
import com.pantasoft.itinstitute.topic.TopicIntegrationTest;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getTopics_NO_CONTENT() {

        ResponseEntity<List> response = this.restTemplate.getForEntity(
                "/topics/no_topic/courses", List.class, "");
        Assert.assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

    @Test
    public void addCourse() {

        Topic topic = new Topic("javascript","Javascript","Javascript Description");
        this.restTemplate.postForEntity("/topic", topic, null);

        Course course = new Course("javascriptcourse","JavaScriptCourse","JavaScript Courses",topic.getId());

        ResponseEntity<Course> response =  this.restTemplate.postForEntity(
                "/topics/javascript/courses", course, Course.class);

        Assert.assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
    }

    @Test
    public void getCoursesByIdTopic() {
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