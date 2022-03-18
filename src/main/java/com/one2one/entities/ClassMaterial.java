package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLASS_MATERIAL")
@NoArgsConstructor
public class ClassMaterial extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASS_MATERIAL_ID")
    private Long id;

    @Column(name = "CLASS_MATERIAL_NAME")
    private String classMaterialName;

    @Column(name = "CLASS_MATERIAL_FILE_PATH")
    private String classMaterialFilePath;
}

