package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACADEMIC_CLASS")
@NoArgsConstructor
public class AcademicClass extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACADEMIC_CLASS_ID")
    private Long id;

    @Column(name = "ACADEMIC_CLASS_NAME", length = 100)
    private String subjectName;

    @Column(name = "ACADEMIC_CLASS_NAME_BN", columnDefinition = "nvarchar(150)")
    private String subjectNameBn;
}

