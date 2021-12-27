package com.one2one.services.impl;

import com.one2one.entities.Subject;
import com.one2one.repositories.SubjectRepository;
import com.one2one.services.SubjectService;
import com.one2one.utils.ServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;

    @Override
    @Transactional
    public Subject save(Subject subject) {
        return repository.save(subject);
    }

    @Override
    @Transactional
    public Subject update(Subject subject) {
        return repository.save(subject);
    }

    @Override
    public Optional<Subject> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Subject> findBySubjectName(String subjectName) {
        return repository.findBySubjectName(subjectName);
    }

    @Override
    public Optional<Subject> findBySubjectNameBn(String subjectNameBn) {
        return repository.findBySubjectNameBn(subjectNameBn);
    }

    @Override
    public void delete(Subject subject) {
        repository.delete(subject);
    }

    @Override
    public Map<String, Object> searchSubject(String subjectName, Long subjectTypeId,
                                             Integer page, Integer size, String sortBy) {
        ServiceHelper<Subject> helper = new ServiceHelper<>(Subject.class);
        return helper.getList(repository.searchSubject(subjectName, subjectTypeId,
                helper.getPageable(sortBy, page, size)), page, size);
    }
}

