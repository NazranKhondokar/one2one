package com.one2one.helper;

import com.one2one.entities.LandingView;
import com.one2one.entities.Subject;
import com.one2one.enums.RecordStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class LandingViewHelper {

//    @Resource
//    private ActiveUserContext context;

    public void setBaseData(LandingView landingView, RecordStatus status, Boolean forUpdate) {
        landingView.setRecordStatus(status);
//        if (forUpdate) {
//            subject.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
//        } else {
//            subject.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
//        }
    }
}
