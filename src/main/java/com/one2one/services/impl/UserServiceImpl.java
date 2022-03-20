package com.one2one.services.impl;

import com.one2one.entities.Subject;
import com.one2one.entities.User;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.SubjectHelper;
import com.one2one.helper.UserHelper;
import com.one2one.repositories.SubjectRepository;
import com.one2one.repositories.UserRepository;
import com.one2one.requests.SubjectRequest;
import com.one2one.services.SubjectService;
import com.one2one.services.UserService;
import com.one2one.utils.ServiceHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl extends UserService {

    private final UserHelper userHelper;

    public UserServiceImpl(UserHelper userHelper, UserRepository userRepository) {
        super(userRepository);
        this.userHelper = userHelper;
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }
}

