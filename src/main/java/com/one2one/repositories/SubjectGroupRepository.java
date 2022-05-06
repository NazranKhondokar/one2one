package com.one2one.repositories;

import com.one2one.entities.SubjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectGroupRepository extends JpaRepository<SubjectGroup, Long> {
}

