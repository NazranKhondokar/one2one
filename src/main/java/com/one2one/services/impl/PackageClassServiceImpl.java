package com.one2one.services.impl;

import com.one2one.entities.Package;
import com.one2one.helper.PackageClassHelper;
import com.one2one.repositories.PackageRepository;
import com.one2one.services.PackageClassService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PackageClassServiceImpl extends PackageClassService {

    private final PackageClassHelper packageClassHelper;

    public PackageClassServiceImpl(PackageClassHelper packageClassHelper,
                                   PackageRepository repository) {
        super(repository);
        this.packageClassHelper = packageClassHelper;
    }

    @Override
    @Transactional
    public void save(Package packageEntity) {
        repository.save(packageEntity);
        return;
    }
}

