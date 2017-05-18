package com.pantasoft.itinstitute.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by massimocaracci on 10/05/2017.
 */
public interface CourseRepository extends CrudRepository<Course,String>{

    List<Course> findByTopicId(String topicId);
}
