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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compositeKey.course"/*, orphanRemoval = true*/)
    private List<CourseClass> courseClasses;
}

