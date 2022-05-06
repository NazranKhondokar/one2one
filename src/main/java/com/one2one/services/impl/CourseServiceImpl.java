package com.one2one.services.impl;

import com.one2one.entities.Course;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.CourseHelper;
import com.one2one.repositories.CourseRepository;
import com.one2one.requests.CourseRequest;
import com.one2one.services.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CourseServiceImpl extends CourseService {

    private final CourseHelper courseHelper;

    public CourseServiceImpl(CourseHelper courseHelper,
                             CourseRepository courseRepository) {
        super(courseRepository);
        this.courseHelper = courseHelper;
    }

    @Override
    @Transactional
    public Course save(CourseRequest request) {
        Course course = request.to(request);
        courseHelper.getData(request, course);
        return repository.save(course);
    }

    @Override
    @Transactional
    public Course update(CourseRequest request) {
        Course course = findCourseById(request.getId());
        request.update(request, course);
        courseHelper.getData(request, course);
        return repository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Course course) {
        repository.delete(course);
    }

    @Override
    @Transactional
    public Course update(Long id, RecordStatus status) {
        Course course = findCourseById(id);
        courseHelper.setBaseData(course, status, true);
        return repository.save(course);
    }
}

