package com.one2one.services.impl;

import com.one2one.entities.LandingClass;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.LandingClassHelper;
import com.one2one.repositories.LandingClassRepository;
import com.one2one.requests.LandingClassRequest;
import com.one2one.services.LandingClassService;
import com.one2one.utils.ServiceHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class LandingClassImpl extends LandingClassService {

    private final LandingClassHelper landingClassHelper;

    public LandingClassImpl(LandingClassHelper helper,LandingClassRepository repository) {
        super(repository);
        this.landingClassHelper = helper;
    }


    @Override
    @Transactional
    public LandingClass save(LandingClassRequest request) {
        LandingClass landingClass = request.to(request);
        return repository.save(landingClass);
    }

    @Override
    public LandingClass update(LandingClassRequest request) {
        LandingClass landingClass = findLandingClassById(request.getLandingClassId());
        request.update(request, landingClass);
        return repository.save(landingClass);
    }

    @Override
    public LandingClass update(Long id, RecordStatus status) {
        LandingClass landingClass = findLandingClassById(id);
        landingClassHelper.setBaseData(landingClass, status, true);
        return repository.save(landingClass);
    }

    @Override
    public Optional<LandingClass> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(LandingClass landingClass) {
        repository.delete(landingClass);
    }

    @Override
    public Map<String, Object> searchLandingClass(Long landingClassId, Integer page, Integer size, String sortBy) {
        ServiceHelper<LandingClass> helper = new ServiceHelper<>(LandingClass.class);
        return helper.getList(
                repository.searchLandingClass(landingClassId, helper.getPageable(sortBy, page, size)),
                page,
                size
        );
    }
}
