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
public class CourseUserCompositeKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Column(name = "STUDENT_USER_ID")
    private Long studentUserId;

    public CourseUserCompositeKey(Course course, Long studentUserId) {
        this.course = course;
        this.studentUserId = studentUserId;
    }
}
