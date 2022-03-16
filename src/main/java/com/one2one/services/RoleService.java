package com.one2one.services;

import com.one2one.entities.Role;
import com.one2one.enums.RoleName;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RoleService {

    protected final RoleRepository repository;

    protected abstract Optional<Role> findById(Long id);

    protected abstract List<Role> findAll();

    protected abstract Optional<Role> findByRoleName(RoleName RoleName);

    public Role findRoleById(Long id) {
        Optional<Role> role = repository.findById(id);
        if (role.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Role was not found for parameters {id=%s}", id));
        }
        return role.get();
    }
}
