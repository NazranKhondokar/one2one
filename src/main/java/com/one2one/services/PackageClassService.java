package com.one2one.services;

import com.one2one.entities.Package;
import com.one2one.repositories.PackageRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PackageClassService {

    protected final PackageRepository repository;

    public abstract void save(Package packageEntity);
}
