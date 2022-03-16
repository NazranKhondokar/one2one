package com.one2one.services;

import com.one2one.entities.LandingView;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.LandingViewRepository;
import com.one2one.requests.LandingViewRequest;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class LandingViewService {
    protected final LandingViewRepository repository;

    protected abstract LandingView save(LandingViewRequest request);

    protected abstract LandingView update(LandingViewRequest request);

    protected abstract LandingView update(Long id, RecordStatus status);

    protected abstract Optional<LandingView> findById(Long id);

    protected abstract void delete(LandingView landingView);

    protected abstract Map<String, Object> searchLandingView(Long landingViewId, Integer page, Integer size, String sortBy);

    public LandingView findLandViewById(Long id) {
        Optional<LandingView> landingView = repository.findById(id);
        if (landingView.isEmpty()) {
            throw new ResourceNotFoundException(String.format("LandingView was not found for parameters {id=%s}", id));
        }
        return landingView.get();
    }
}
