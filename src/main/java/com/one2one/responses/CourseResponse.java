package com.one2one.responses;

import com.one2one.entities.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseResponse {

    private Long id;
    private String courseName;
    private String courseNameBn;

    public static CourseResponse from(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setCourseNameBn(course.getCourseNameBn());
        return response;
    }
}

