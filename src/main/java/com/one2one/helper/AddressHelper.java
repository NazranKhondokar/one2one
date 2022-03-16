package com.one2one.helper;


import com.one2one.entities.Address;
import com.one2one.enums.RecordStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressHelper {
    //    @Resource
//    private ActiveUserContext context;

    public void setBaseData(Address address, RecordStatus status, Boolean forUpdate) {
        address.setRecordStatus(status);
//        if (forUpdate) {
//            subject.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
//        } else {
//            subject.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
//        }
    }
}
