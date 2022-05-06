package com.one2one.requests;

import com.one2one.entities.Course;
import com.one2one.entities.CourseClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CourseClassRequest {

    @NotNull
    private Long subjectId;

    @NotNull
    private Long studentUserId;

    @NotNull
    private Long teacherUserId;
    private Integer completion;
    private String zoomCredential;

    public CourseClass toEntity(Course course) {
        CourseClass courseClass = new CourseClass();
        courseClass.setCourseId(course.getId());
        courseClass.setCompletion(this.completion);
        courseClass.setZoomCredential(this.zoomCredential);
        courseClass.setStudentUserId(this.studentUserId);
        courseClass.setTeacherUserId(this.teacherUserId);
        courseClass.setSubjectId(this.subjectId);
        course.getCourseClasses().add(courseClass);
        return courseClass;
    }
}

