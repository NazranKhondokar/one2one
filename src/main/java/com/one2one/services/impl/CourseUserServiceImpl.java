package com.one2one.services.impl;

import com.one2one.entities.Course;
import com.one2one.helper.CourseClassHelper;
import com.one2one.helper.CourseUserHelper;
import com.one2one.repositories.CourseRepository;
import com.one2one.services.CourseUserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseUserServiceImpl extends CourseUserService {

    private final CourseUserHelper courseUserHelper;

    public CourseUserServiceImpl(CourseUserHelper courseUserHelper,
                                 CourseRepository repository) {
        super(repository);
        this.courseUserHelper = courseUserHelper;
    }

    @Override
    @Transactional
    public void save(Course course) {
        repository.save(course);
        return;
    }
}

