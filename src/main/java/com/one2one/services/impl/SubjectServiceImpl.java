package com.one2one.services.impl;

import com.one2one.entities.Subject;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.helper.SubjectHelper;
import com.one2one.repositories.SubjectRepository;
import com.one2one.requests.SubjectRequest;
import com.one2one.services.SubjectService;
import com.one2one.utils.ServiceHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class  SubjectServiceImpl extends SubjectService {

    private final SubjectHelper subjectHelper;

    public SubjectServiceImpl(SubjectHelper subjectHelper,
                              SubjectRepository subjectRepository) {
        super(subjectRepository);
        this.subjectHelper = subjectHelper;
    }

    @Override
    @Transactional
    public Subject save(SubjectRequest request) {
        Subject subject = request.to(request);
        return repository.save(subject);
    }

    @Override
    @Transactional
    public Subject update(SubjectRequest request) {
        Subject subject = findSubjectById(request.getId());
        request.update(request, subject);
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
        return helper.getList(
                repository.searchSubject(subjectName, subjectTypeId,
                helper.getPageable(sortBy, page, size)),
                page,
                size);
    }

    @Override
    @Transactional
    public Subject update(Long id, RecordStatus status) {
        Subject subject = findSubjectById(id);
        subjectHelper.setBaseData(subject, status, true);
        return repository.save(subject);
    }
}

