package com.one2one.services;

import com.one2one.entities.Address;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.AddressRepository;
import com.one2one.requests.AddressRequest;
import lombok.RequiredArgsConstructor;

import javax.swing.text.TabableView;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AddressService {
    protected final AddressRepository repository;

    protected abstract Address save(AddressRequest request);

    protected abstract Address update(AddressRequest request);

    protected abstract Address update(Long id, RecordStatus status);

    protected abstract Optional<Address> findById(Long id);
    protected abstract Optional<Address> findByAddress(String address);

    protected abstract void delete(Address address);

    protected abstract Map<String, Object> searchAddress(Integer page, Integer size, String sortBy);

    public Address findAddressById(Long id){
        Optional<Address> address = repository.findById(id);

        if(address.isEmpty()){
            throw new ResourceNotFoundException(String.format("Address was not found for parameters {id=%s}", id));
        }
        return address.get();
    }
}
