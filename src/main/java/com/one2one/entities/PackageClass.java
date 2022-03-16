package com.one2one.entities;

import com.one2one.entities.composite.PackageClassCompositeKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PACKAGE_CLASS")
@NoArgsConstructor
public class PackageClass extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @EmbeddedId
    private PackageClassCompositeKey compositeKey;

    @Column(name = "PACKAGE_CLASS_IS_PAID")
    private Boolean packageClassIsPaid;

    @Column(name = "PACKAGE_CLASS_IS_ENROLLED")
    private Boolean packageClassIsEnrolled;
}

