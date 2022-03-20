package com.one2one.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ADDRESS")
@NoArgsConstructor
public class Address extends BaseEntity{
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "ADDRESS", columnDefinition = "nvarchar(255)")
    private String addressDetail;

    @Column(name = "POST")
    private Long postId;

    @Column(name = "THANA")
    private Long thanaId;

    @Column(name = "DISTRICT")
    private Long districtId;

    @Column(name = "DIVISION")
    private Long divisionId;

}
