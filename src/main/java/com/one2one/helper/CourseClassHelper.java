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

    public void setCourseClass(CourseClassListRequest dto, Course course,
                                RecordStatus recordStatus) {
        Map<String, Integer> courseClassIdToVersion = new HashMap<>();

        for (CourseClass courseClass : course.getCourseClasses()) {
            courseClassIdToVersion.put("%s-%s-%s-%s-%s".formatted(course.getId(),
                    courseClass.getCompositeKey().getAcademicClassId(),
                    courseClass.getCompositeKey().getSubjectId(),
                    courseClass.getCompositeKey().getStudentUserId(),
                    courseClass.getCompositeKey().getTeacherUserId()),
                    courseClass.getRecordVersion());
        }
        course.getCourseClasses().clear();

        if (Objects.isNull(dto.getCourseClasses())) return;

        for (CourseClassRequest courseClassRequest : dto.getCourseClasses()) {
            CourseClass courseClass = new CourseClass();
            courseClass.setCourseClassIsPaid(courseClassRequest.getCourseClassIsPaid());
            courseClass.setCourseClassIsEnrolled(courseClassRequest.getCourseClassIsEnrolled());
            courseClass.setRecordVersion(courseClassIdToVersion
                    .getOrDefault("%s-%s-%s-%s-%s".formatted(
                            course.getId(),
                            courseClassRequest.getAcademicClassId(),
                            courseClassRequest.getSubjectId(),
                            courseClassRequest.getStudentUserId(),
                            courseClassRequest.getTeacherUserId()), 0));
            courseClass.setCompositeKey(new CourseClassCompositeKey(
                    course, courseClassRequest.getAcademicClassId(),
                    courseClassRequest.getSubjectId(),
                    courseClassRequest.getStudentUserId(),
                    courseClassRequest.getTeacherUserId()));
            setBaseEntityProperties(courseClass, recordStatus);
            course.getCourseClasses().add(courseClass);
        }
    }
}
