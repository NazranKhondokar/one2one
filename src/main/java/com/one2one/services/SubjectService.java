package com.one2one.services;

import com.one2one.entities.Subject;

import java.util.Map;
import java.util.Optional;

public interface SubjectService {

    Subject save(Subject subject);

    Subject update(Subject subject);

    Optional<Subject> findById(Long id);

    Optional<Subject> findBySubjectName(String subjectName);

    Optional<Subject> findBySubjectNameBn(String subjectNameBn);

    void delete(Subject subject);

    Map<String, Object> searchSubject(String subjectName, Long subjectTypeId, Integer page, Integer size, String sortBy);
}
