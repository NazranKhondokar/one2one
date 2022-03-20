package com.one2one.repositories;

import com.one2one.entities.Address;
import com.one2one.entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

   Optional<Address> findByAddressDetail(String addressDetail);

   @Query("SELECT s FROM Address s")
   Page<Address> searchAddress(Pageable pageable);

}
