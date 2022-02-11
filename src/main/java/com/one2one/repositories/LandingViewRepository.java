package com.one2one.repositories;

import com.one2one.entities.LandingView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingViewRepository extends JpaRepository<LandingView, Long> {

   @Query("SELECT l FROM LandingView l WHERE "+ "(:landingViewId IS NULL OR l.view_id = :landingViewId)")
   Page<LandingView> searchLandingView(Long landingViewId, Pageable pageable);

}
