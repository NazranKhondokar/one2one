package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COURSE_USER")
@NoArgsConstructor
public class CourseUser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_USER_ID")
    private Long id;

    @JoinColumn(name = "COURSE_ID")
    private Long courseId;

    @Column(name = "STUDENT_USER_ID")
    private Long studentUserId;

    @Column(name = "COMPLETION")
    private Integer completion;

    @Column(name = "HAS_COURSE_ENROLLED")
    private Boolean hasCourseEnrolled;
}

