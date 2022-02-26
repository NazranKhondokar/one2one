package com.one2one.services;

import com.one2one.entities.LandingClass;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.LandingClassRepository;

import com.one2one.requests.LandingClassRequest;
import com.one2one.requests.LandingViewRequest;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class LandingClassService {
    protected final LandingClassRepository repository;

    protected abstract LandingClass save(LandingClassRequest request);

    protected abstract LandingClass update(LandingClassRequest request);

    protected abstract LandingClass update(Long id, RecordStatus status);

    protected abstract Optional<LandingClass> findById(Long id);

    protected abstract void delete(LandingClass landingClass);

    protected abstract Map<String, Object> searchLandingClass(Long landingClassId, Integer page, Integer size, String sortBy);

    public LandingClass findLandingClassById(Long id) {
        Optional<LandingClass> landingClass = repository.findById(id);
        if (landingClass.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Subject was not found for parameters {id=%s}", id));
        }
        return landingClass.get();
    }
}
