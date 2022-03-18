package com.one2one.services.impl;

import com.one2one.entities.Course;
import com.one2one.helper.CourseClassHelper;
import com.one2one.repositories.CourseRepository;
import com.one2one.services.CourseClassService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseClassServiceImpl extends CourseClassService {

    private final CourseClassHelper courseClassHelper;

    public CourseClassServiceImpl(CourseClassHelper courseClassHelper,
                                  CourseRepository repository) {
        super(repository);
        this.courseClassHelper = courseClassHelper;
    }

    @Override
    @Transactional
    public void save(Course course) {
        repository.save(course);
        return;
    }
}

