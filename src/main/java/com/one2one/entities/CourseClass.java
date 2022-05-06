package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COURSE_CLASS")
@NoArgsConstructor
public class CourseClass extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_CLASS_ID")
    private Long id;

    @JoinColumn(name = "COURSE_ID")
    private Long courseId;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "STUDENT_USER_ID")
    private Long studentUserId;

    @Column(name = "TEACHER_USER_ID")
    private Long teacherUserId;

    @Column(name = "COMPLETION")
    private Integer completion;

    @Column(name = "ZOOM_CREDENTIAL")
    private String zoomCredential;

    @OneToOne
    @JoinColumn(name = "CLASS_MATERIAL_ID")
    private ClassMaterial classMaterial;
}

