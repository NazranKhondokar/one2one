package com.one2one.services;

import com.one2one.entities.Course;
import com.one2one.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CourseUserService {

    protected final CourseRepository repository;

    public abstract void save(Course course);
}
