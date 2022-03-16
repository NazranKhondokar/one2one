package com.one2one.requests;

import com.one2one.entities.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AddressRequest {
    private Long Id;

    @NotBlank
    private String address;

    private Long postId;
    private Long thanaId;
    private Long districtId;
    private Long divisionId;

    public Address to(AddressRequest request){
        Address address = new Address();
        BeanUtils.copyProperties(request,address);
        return address;
    }

    public void update(AddressRequest request, Address address){
        BeanUtils.copyProperties(request,address);
    }

}
