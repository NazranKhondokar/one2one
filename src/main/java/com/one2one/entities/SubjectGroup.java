package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SUBJECT_GROUP")
@NoArgsConstructor
public class SubjectGroup extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_GROUP_ID")
    private Long id;

    @Column(name = "SUBJECT_GROUP_NAME", length = 100)
    private String subjectName;

    @Column(name = "SUBJECT_GROUP_NAME_BN", columnDefinition = "nvarchar(150)")
    private String subjectNameBn;
}

