package com.pantasoft.itinstitute.course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by massimocaracci on 10/05/2017.
 */

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public List<Course> getAllCourses(String topicId){
        List<Course> listCourses = new ArrayList<>();

        courseRepository.findByTopicId(topicId).forEach(listCourses::add);

        return listCourses;
    }

    public Optional<Course> getCourse(String id) {
        return Optional.ofNullable( courseRepository.findOne(id));
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        courseRepository.delete(id);
    }
}
