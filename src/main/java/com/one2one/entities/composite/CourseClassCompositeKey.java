package com.one2one.entities.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one2one.entities.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class CourseClassCompositeKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "STUDENT_USER_ID")
    private Long studentUserId;

    @Column(name = "TEACHER_USER_ID")
    private Long teacherUserId;

    public CourseClassCompositeKey(Course course, Long subjectId,
                                   Long studentUserId, Long teacherUserId) {
        this.course = course;
        this.subjectId = subjectId;
        this.studentUserId = studentUserId;
        this.teacherUserId = teacherUserId;
    }
}
