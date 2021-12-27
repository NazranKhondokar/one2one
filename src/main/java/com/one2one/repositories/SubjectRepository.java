package com.one2one.repositories;

import com.one2one.entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectName(String subjectName);

    Optional<Subject> findBySubjectNameBn(String subjectNameBn);

    @Query("SELECT s FROM Subject s WHERE " +
            "LOWER(s.subjectName) LIKE LOWER(CONCAT('%', :subjectName, '%')) AND " +
            "(:subjectTypeId IS NULL OR s.subjectTypeId = :subjectTypeId)")
    Page<Subject> searchSubject(String subjectName, Long subjectTypeId, Pageable pageable);
}

