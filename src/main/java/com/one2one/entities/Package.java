package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "PACKAGE")
@NoArgsConstructor
public class Package extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PACKAGE_ID")
    private Long id;

    @Column(name = "PACKAGE_NAME", length = 100)
    private String packageName;

    @Column(name = "PACKAGE_NAME_BN", columnDefinition = "nvarchar(150)")
    private String packageNameBn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compositeKey.packageEntity"/*, orphanRemoval = true*/)
    private List<PackageClass> packageClasses;
}

