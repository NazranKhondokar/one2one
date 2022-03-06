package com.one2one.services.impl;

import com.one2one.entities.Role;
import com.one2one.enums.RoleName;
import com.one2one.repositories.RoleRepository;
import com.one2one.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl extends RoleService {

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findByRoleName(RoleName roleName) {
        return repository.findByName(roleName);
    }
}

