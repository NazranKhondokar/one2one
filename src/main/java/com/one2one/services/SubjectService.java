package com.one2one.services;

import com.one2one.entities.Subject;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.SubjectRepository;
import com.one2one.requests.SubjectRequest;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class SubjectService {

    protected final SubjectRepository repository;

    protected abstract Subject save(SubjectRequest request);

    protected abstract Subject update(SubjectRequest request);

    protected abstract Subject update(Long id, RecordStatus status);

    protected abstract Optional<Subject> findById(Long id);

    protected abstract Optional<Subject> findBySubjectName(String subjectName);

    protected abstract Optional<Subject> findBySubjectNameBn(String subjectNameBn);

    protected abstract void delete(Subject subject);

    protected abstract Map<String, Object> searchSubject(String subjectName, Long subjectTypeId, Integer page, Integer size, String sortBy);

    public Subject findSubjectById(Long id) {
        Optional<Subject> subject = repository.findById(id);
        if (subject.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Subject was not found for parameters {id=%s}", id));
        }
        return subject.get();
    }
}
