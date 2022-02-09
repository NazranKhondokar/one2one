package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LANDINGVIEW")
@NoArgsConstructor

public class LandingView extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VIEW_ID")
    private Long view_id;

    @Column(name = "CLASS_ID")
    private Long class_id;

    @Column(name = "PROMOTION_ID")
    private Long promotion_id;

    @Column(name = "REVIEW_ID")
    private Long review_id;
}
