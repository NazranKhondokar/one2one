package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SUBJECT")
@NoArgsConstructor
public class Subject extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID")
    private Long id;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @Column(name = "SUBJECT_NAME_BN", columnDefinition = "nvarchar(150)")
    private String subjectNameBn;

    @Column(name = "SUBJECT_CODE")
    private String subjectCode;

    @Column(name = "SUBJECT_TYPE_ID")
    private Long subjectTypeId;
}

