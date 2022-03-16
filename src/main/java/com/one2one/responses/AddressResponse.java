package com.one2one.responses;

import com.one2one.entities.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AddressResponse {
    private Long Id;
    private String address;
    private Long postId;
    private Long thanaId;
    private Long districtId;
    private Long divisionId;

    public static AddressResponse from(Address address){
        AddressResponse response = new AddressResponse();
        BeanUtils.copyProperties(address,response);
        return response;
    }
}
