package com.one2one.services;

import com.one2one.entities.Subject;
import com.one2one.entities.User;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class UserService {

    protected final UserRepository repository;
    protected abstract User save(User user);

    public User findUserById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User was not found for parameters {id=%s}", id));
        }
        return user.get();
    }
}
