package com.one2one.repositories;

import com.one2one.entities.LandingClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingClassRepository extends JpaRepository<LandingClass, Long> {

    @Query("SELECT l FROM LandingClass l WHERE "+ "(:landingClassId IS NULL OR l.landingClassId = :landingClassId)")
    Page<LandingClass> searchLandingClass(Long landingClassId, Pageable pageable);
}
