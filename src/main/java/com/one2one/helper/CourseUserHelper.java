package com.one2one.helper;

import com.one2one.entities.BaseEntity;
import com.one2one.entities.Course;
import com.one2one.entities.CourseClass;
import com.one2one.entities.CourseUser;
import com.one2one.entities.composite.CourseClassCompositeKey;
import com.one2one.entities.composite.CourseUserCompositeKey;
import com.one2one.enums.RecordStatus;
import com.one2one.requests.CourseClassListRequest;
import com.one2one.requests.CourseClassRequest;
import com.one2one.requests.CourseUserListRequest;
import com.one2one.requests.CourseUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CourseUserHelper {

    private void setBaseEntityProperties(BaseEntity baseEntity, RecordStatus recordStatus) {
        baseEntity.setCreatedBy(1L);
        baseEntity.setUpdatedBy(1L);
        baseEntity.setRecordStatus(recordStatus);
    }

    public void setCourseUser(CourseUserListRequest dto, Course course,
                              RecordStatus recordStatus) {
        Map<String, Integer> courseUserIdToVersion = new HashMap<>();

        for (CourseUser courseUser : course.getCourseUsers()) {
            courseUserIdToVersion.put("%s-%s-%s".formatted(course.getId(),
                    courseUser.getCompositeKey().getStudentUserId()),
                    courseUser.getRecordVersion());
        }
        course.getCourseUsers().clear();

        if (Objects.isNull(dto.getCourseUsers())) return;

        for (CourseUserRequest courseUserRequest : dto.getCourseUsers()) {
            CourseUser courseUser = new CourseUser();
            courseUser.setCompletion(courseUserRequest.getCompletion());
            courseUser.setHasCourseEnrolled(courseUserRequest.getHasCourseEnrolled());
            courseUser.setRecordVersion(courseUserIdToVersion
                    .getOrDefault("%s-%s-%s-%s".formatted(
                            course.getId(),
                            courseUserRequest.getStudentUserId()), 0));
            courseUser.setCompositeKey(new CourseUserCompositeKey(
                    course,
                    courseUserRequest.getStudentUserId()));
            setBaseEntityProperties(courseUser, recordStatus);
            course.getCourseUsers().add(courseUser);
        }
    }
}
