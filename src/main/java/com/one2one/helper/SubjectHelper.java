package com.one2one.helper;

import com.one2one.entities.Subject;
import com.one2one.enums.RecordStatus;
//import com.one2one.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubjectHelper {

//    @Resource
//    private ActiveUserContext context;

    public void setBaseData(Subject subject, RecordStatus status, Boolean forUpdate) {
        subject.setRecordStatus(status);
//        if (forUpdate) {
//            subject.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
//        } else {
//            subject.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
//        }
    }
}
