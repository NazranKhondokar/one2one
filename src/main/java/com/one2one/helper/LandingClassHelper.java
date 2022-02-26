package com.one2one.helper;

import com.one2one.entities.LandingClass;
import com.one2one.enums.RecordStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LandingClassHelper {
    //    @Resource
//    private ActiveUserContext context;

    public void setBaseData(LandingClass landingClass, RecordStatus status, Boolean forUpdate) {
        landingClass.setRecordStatus(status);
//        if (forUpdate) {
//            subject.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
//        } else {
//            subject.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
//        }
    }
}
