package com.one2one.validators;

import com.one2one.entities.Subject;
import com.one2one.requests.SubjectRequest;
import com.one2one.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.one2one.constant.ValidatorConstants.ALREADY_EXIST;
import static com.one2one.utils.StringUtils.isNotEmpty;


@Component
@RequiredArgsConstructor
public class SubjectValidator implements Validator {

    private final SubjectService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return SubjectRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        SubjectRequest request = (SubjectRequest) target;

        if (isNotEmpty(request.getSubjectName())) {
            Optional<Subject> subject = service.findBySubjectName(request.getSubjectName());
            if (subject.isPresent()) {
                error.rejectValue("subjectName", null, ALREADY_EXIST);
            }
        }

        if (isNotEmpty(request.getSubjectNameBn())) {
            Optional<Subject> subjectBn = service.findBySubjectNameBn(request.getSubjectNameBn());
            if (subjectBn.isPresent()) {
                error.rejectValue("subjectNameBn", null, ALREADY_EXIST);
            }
        }
    }
}

