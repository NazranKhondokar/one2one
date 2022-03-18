package com.one2one.entities;

import com.one2one.entities.composite.CourseClassCompositeKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COURSE_CLASS")
@NoArgsConstructor
public class CourseClass extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @EmbeddedId
    private CourseClassCompositeKey compositeKey;

    @Column(name = "COMPLETION")
    private Integer completion;

    @Column(name = "ZOOM_CREDENTIAL")
    private String zoomCredential;

    @OneToOne
    @JoinColumn(name = "CLASS_MATERIAL_ID")
    private ClassMaterial classMaterial;
}

