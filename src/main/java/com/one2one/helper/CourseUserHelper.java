package com.one2one.helper;

import com.one2one.entities.BaseEntity;
import com.one2one.entities.Course;
import com.one2one.entities.CourseUser;
import com.one2one.enums.RecordStatus;
import com.one2one.requests.CourseUserListRequest;
import com.one2one.requests.CourseUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CourseUserHelper {

    private void setBaseEntityProperties(BaseEntity baseEntity, RecordStatus recordStatus) {
        baseEntity.setCreatedBy(1L);
        baseEntity.setUpdatedBy(1L);
        baseEntity.setRecordStatus(recordStatus);
    }

    public void setCourseUserDtoToEntity(CourseUserListRequest dto, Course course, RecordStatus recordStatus) {
        course.getCourseUsers().clear();

        if (Objects.isNull(dto.getCourseUsers()) || dto.getCourseUsers().isEmpty()) return;

        for (CourseUserRequest courseUserRequest : dto.getCourseUsers()) {
            CourseUser courseUser = courseUserRequest.toEntity(course);
            setBaseEntityProperties(courseUser, recordStatus);
        }
    }
}
