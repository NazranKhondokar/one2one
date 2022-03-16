package com.one2one.entities.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one2one.entities.Package;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class PackageClassCompositeKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACKAGE_ID")
    private Package packageEntity;

    @Column(name = "ACADEMIC_CLASS_ID")
    private Long academicClassId;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @Column(name = "STUDENT_USER_ID")
    private Long studentUserId;

    @Column(name = "TEACHER_USER_ID")
    private Long teacherUserId;

    public PackageClassCompositeKey(Package packageEntity, Long academicClassId, Long subjectId,
                                    Long studentUserId, Long teacherUserId) {
        this.packageEntity = packageEntity;
        this.academicClassId = academicClassId;
        this.subjectId = subjectId;
        this.studentUserId = studentUserId;
        this.teacherUserId = teacherUserId;
    }
}
