package com.pantasoft.itinstitute.course;

import com.pantasoft.itinstitute.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by massimocaracci on 10/05/2017.
 */

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/topics/{topicId}/courses")
    public ResponseEntity getAllCourses(@PathVariable String topicId){
        List<Course> courses = courseService.getAllCourses(topicId);

        if (courses.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @GetMapping("/topics/courses/{courseId}")
    public ResponseEntity getCourse(@PathVariable String courseId){
        Optional<Course> course = courseService.getCourse(courseId);

        if (course.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(course.get());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping( value = "/topics/{topicId}/courses")
    public ResponseEntity addCourse(@RequestBody Course course, @PathVariable String topicId){
        course.setTopic(new Topic(topicId,"",""));
        Course res = courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping(value = "/topics/{topicId}/courses/{id}")
    public ResponseEntity updateCourse(@RequestBody Course course, @PathVariable String topicId, @PathVariable String id){
        course.setTopic(new Topic(topicId,"",""));
        Course res = courseService.updateCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping(value = "/topics/{topicId}/courses/{courseId}")
    public ResponseEntity deleteTopic(@PathVariable String courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
