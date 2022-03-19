package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LANDING_CLASS")
@NoArgsConstructor
public class LandingClass extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LANDING_CLASS_ID")
    private Long landingClassId;

    @Column(name = "LANDING_CLASS_NAME")
    private String landingClassName;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "CLASS_ID")
    private Long classId;

    @Column(name = "DESCRIPTION")
    private String description;
}
