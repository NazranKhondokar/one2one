package com.one2one.services;

import com.one2one.entities.Subject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubjectService {

    Map<String, Object> getList(String[] sortable, String[] searchable, String sortBy, String search, Integer page, Integer size);

    List<Subject> findAll(String[] sortable, String sortBy);

    Subject save(Subject subject);

    Subject update(Subject subject);

    Optional<Subject> findById(Long id);

    Optional<Subject> findBySubjectName(String subjectName);

    Optional<Subject> findBySubjectNameBn(String subjectNameBn);

    void delete(Subject subject);
}
