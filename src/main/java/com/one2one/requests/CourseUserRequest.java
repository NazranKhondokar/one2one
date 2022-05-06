package com.one2one.requests;

import com.one2one.entities.Course;
import com.one2one.entities.CourseClass;
import com.one2one.entities.CourseUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CourseUserRequest {

    @NotNull
    private Long subjectId;

    @NotNull
    private Long studentUserId;

    private Integer completion;
    private Boolean hasCourseEnrolled;

    public CourseUser toEntity(Course course) {
        CourseUser courseUser = new CourseUser();
        courseUser.setCourseId(course.getId());
        courseUser.setCompletion(this.completion);
        courseUser.setStudentUserId(this.studentUserId);
        course.getCourseUsers().add(courseUser);
        return courseUser;
    }
}

