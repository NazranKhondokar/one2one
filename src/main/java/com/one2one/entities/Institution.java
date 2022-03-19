package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "INSTITUTION")
@NoArgsConstructor
public class Institution extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTITUTION_ID")
    private Long id;

    @Column(name = "INSTITUTION_NAME", length = 100)
    private String subjectName;

    @Column(name = "INSTITUTION_NAME_BN", columnDefinition = "nvarchar(150)")
    private String subjectNameBn;
}

