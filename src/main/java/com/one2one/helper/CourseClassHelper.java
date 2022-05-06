package com.one2one.helper;

import com.one2one.entities.BaseEntity;
import com.one2one.entities.Course;
import com.one2one.entities.CourseClass;
import com.one2one.entities.composite.CourseClassCompositeKey;
import com.one2one.enums.RecordStatus;
import com.one2one.requests.CourseClassListRequest;
import com.one2one.requests.CourseClassRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CourseClassHelper {

    private void setBaseEntityProperties(BaseEntity baseEntity, RecordStatus recordStatus) {
        baseEntity.setCreatedBy(1L);
        baseEntity.setUpdatedBy(1L);
        baseEntity.setRecordStatus(recordStatus);
    }

    public void setCourseClassDtoToEntity(CourseClassListRequest dto, Course course, RecordStatus recordStatus) {
        course.getCourseClasses().clear();

        if (Objects.isNull(dto.getCourseClasses()) || dto.getCourseClasses().isEmpty()) return;

        for (CourseClassRequest courseClassRequest : dto.getCourseClasses()) {
            CourseClass courseClass = courseClassRequest.toEntity(course);
            setBaseEntityProperties(courseClass, recordStatus);
        }
    }
}
