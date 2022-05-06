package com.one2one.requests;

import com.one2one.entities.Course;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CourseRequest {

    private Long id;

    @NotBlank
    private String courseName;
    private String courseNameBn;
    private Integer regularPrice;
    private Integer discountedPrice;
    private Integer trialPrice;
    private Integer trialDays;

    @NotNull
    private Long academicClassId;

    @NotNull
    private Long subjectGroupId;

    public Course to(CourseRequest request) {
        Course course = new Course();
        BeanUtils.copyProperties(request, course);
        return course;
    }

    public void update(CourseRequest request, Course course) {
        BeanUtils.copyProperties(request, course);
    }
}

