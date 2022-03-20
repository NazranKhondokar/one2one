package com.one2one.helper;


import com.one2one.entities.Address;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.AddressRepository;
import com.one2one.requests.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final AddressRepository addressRepository;

    public void setBaseData(Address address, RecordStatus status, Boolean forUpdate) {
        address.setRecordStatus(status);
//        if (forUpdate) {
//            subject.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
//        } else {
//            subject.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
//        }
    }

    public Function<AddressRequest, Address> address = AddressRequest::to;

    public List<Address> getAddresses(List<AddressRequest> addressRequests) {
        return addressRequests.stream().map(address).collect(Collectors.toList());
    }
}
