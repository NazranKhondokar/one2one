package com.one2one.entities;

import com.one2one.entities.composite.CourseClassCompositeKey;
import com.one2one.entities.composite.CourseUserCompositeKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COURSE_USER")
@NoArgsConstructor
public class CourseUser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @EmbeddedId
    private CourseUserCompositeKey compositeKey;

    @Column(name = "COMPLETION")
    private Integer completion;

    @Column(name = "HAS_COURSE_ENROLLED")
    private Boolean hasCourseEnrolled;
}

