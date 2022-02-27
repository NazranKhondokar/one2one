package com.one2one.services.impl;

import com.one2one.entities.LandingView;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.LandingViewHelper;
import com.one2one.repositories.LandingViewRepository;
import com.one2one.requests.LandingViewRequest;
import com.one2one.services.LandingViewService;
import com.one2one.utils.ServiceHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class LandingViewImpl extends LandingViewService {
    private final LandingViewHelper landingViewHelper;

    public LandingViewImpl(LandingViewHelper landingViewHelper,LandingViewRepository repository) {
        super(repository);
        this.landingViewHelper = landingViewHelper;
    }

    @Override
    @Transactional
    public LandingView save(LandingViewRequest request) {
        LandingView landingView = request.to(request);
        return repository.save(landingView);
    }

    @Override
    @Transactional
    public LandingView update(LandingViewRequest request) {
        LandingView landingView = findLandViewById(request.getLandingview_id());
        request.update(request, landingView);
        return repository.save(landingView);
    }



    @Override
    public LandingView update(Long id, RecordStatus status) {
        LandingView landingView = findLandViewById(id);
        landingViewHelper.setBaseData(landingView, status, true);
        return repository.save(landingView);
    }

    @Override
    public Optional<LandingView> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(LandingView landingView) {
        repository.delete(landingView);
    }

    @Override
    public Map<String, Object> searchLandingView(Long landingViewId, Integer page, Integer size, String sortBy) {
        ServiceHelper<LandingView> helper = new ServiceHelper<>(LandingView.class);
        return helper.getList(
                repository.searchLandingView(landingViewId, helper.getPageable(sortBy, page, size)),
                page,
                size
        );
    }
}
