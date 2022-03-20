package com.one2one.validators;

import com.one2one.entities.Address;
import com.one2one.entities.Subject;
import com.one2one.requests.AddressRequest;
import com.one2one.requests.SubjectRequest;
import com.one2one.services.impl.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.one2one.constant.ValidatorConstants.ALREADY_EXIST;
import static com.one2one.utils.StringUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class AddressValidator implements Validator {
    private final AddressServiceImpl service;

    @Override
    public boolean supports(Class<?> clazz) {
        return AddressRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {
        AddressRequest request = (AddressRequest) target;

        if (isNotEmpty(request.getAddressDetail())) {
            Optional<Address> address = service.findByAddress(request.getAddressDetail());
            if (address.isPresent()) {
                error.rejectValue("addressDetail", null, ALREADY_EXIST);
            }
        }
    }
}
