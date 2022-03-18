package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "COURSE")
@NoArgsConstructor
public class Course extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;

    @Column(name = "COURSE_NAME", length = 100)
    private String courseName;

    @Column(name = "COURSE_NAME_BN", columnDefinition = "nvarchar(150)")
    private String courseNameBn;

    @Column(name = "REGULAR_PRICE")
    private Integer regularPrice;

    @Column(name = "DISCOUNTED_PRICE")
    private Integer discountedPrice;

    @Column(name = "TRIAL_PRICE")
    private Integer trialPrice;

    @Column(name = "TRIAL_DAYS")
    private Integer trialDays;

    @OneToOne
    @JoinColumn(name = "ACADEMIC_CLASS_ID")
    private AcademicClass academicClass;

    @OneToOne
    @JoinColumn(name = "SUBJECT_GROUP_ID")
    private SubjectGroup subjectGroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compositeKey.course"/*, orphanRemoval = true*/)
    private List<CourseClass> courseClasses;
}

