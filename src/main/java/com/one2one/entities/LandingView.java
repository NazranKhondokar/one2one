package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LANDING_VIEW")
@NoArgsConstructor
public class LandingView extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LANDING_VIEW_ID")
    private Long landingViewId;

    @Column(name = "CLASS_ID")
    private Long classId;

    @Column(name = "PROMOTION_ID")
    private Long promotionId;

    @Column(name = "REVIEW_ID")
    private Long reviewId;

}
