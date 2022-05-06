package com.one2one.services;

import com.one2one.entities.Course;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.CourseRepository;
import com.one2one.requests.CourseRequest;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CourseService {

    protected final CourseRepository repository;
    protected abstract Course save(CourseRequest request);
    protected abstract Course update(CourseRequest request);
    protected abstract Course update(Long id, RecordStatus status);
    protected abstract Optional<Course> findById(Long id);
    protected abstract void delete(Course course);

    public Course findCourseById(Long id) {
        Optional<Course> course = repository.findById(id);
        if (course.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course was not found for parameters {id=%s}", id));
        }
        return course.get();
    }
}
