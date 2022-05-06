package com.one2one.repositories;

import com.one2one.entities.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicClassRepository extends JpaRepository<AcademicClass, Long> {
}

