package com.one2one.services.impl;

import com.one2one.entities.Address;
import com.one2one.entities.Subject;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.AddressHelper;
import com.one2one.repositories.AddressRepository;
import com.one2one.requests.AddressRequest;
import com.one2one.services.AddressService;
import com.one2one.utils.ServiceHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class AddressServiceImpl extends AddressService {
    private final AddressHelper addressHelper;

    public AddressServiceImpl(AddressHelper addressHelper, AddressRepository addressRepository) {
        super(addressRepository);
        this.addressHelper = addressHelper;
    }

    @Override
    @Transactional
    public Address save(AddressRequest request) {
        Address address = request.to(request);
        return repository.save(address);
    }

    @Override
    @Transactional
    public Address update(AddressRequest request) {
        Address address = findAddressById(request.getId());
        request.update(request,address);
        return repository.save(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Address> findByAddress(String address) {
        return repository.findByAddress(address);
    }

    @Override
    public void delete(Address address) {
        repository.delete(address);
    }

    @Override
    public Map<String, Object> searchAddress(Integer page, Integer size, String sortBy) {

        ServiceHelper<Address> helper = new ServiceHelper<>(Address.class);
        return helper.getList(repository.searchAddress(helper.getPageable(sortBy, page, size)), page, size);
    }

    @Override
    public Address update(Long id, RecordStatus status) {
        Address address = findAddressById(id);
        addressHelper.setBaseData(address,status,true);
        return repository.save(address);
    }
}
