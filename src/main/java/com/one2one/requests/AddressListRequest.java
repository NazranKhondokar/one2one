package com.one2one.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddressListRequest {

    @NotNull
    private List<AddressRequest> addresses;
}
