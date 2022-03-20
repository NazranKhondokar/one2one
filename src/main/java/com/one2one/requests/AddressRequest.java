package com.one2one.requests;

import com.one2one.entities.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AddressRequest {

    private Long id;

    @NotBlank
    private String addressDetail;

    private Long postId;
    private Long thanaId;
    private Long districtId;
    private Long divisionId;

    public Address to(){
        Address address = new Address();
        address.setId(id);
        address.setAddressDetail(addressDetail);
        address.setPostId(postId);
        address.setThanaId(thanaId);
        address.setDistrictId(districtId);
        address.setDivisionId(divisionId);
        return address;
    }

    public Address to(AddressRequest request){
        Address address = new Address();
        BeanUtils.copyProperties(request,address);
        return address;
    }

    public void update(AddressRequest request, Address address){
        BeanUtils.copyProperties(request,address);
    }

}
