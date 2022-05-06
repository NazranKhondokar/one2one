package com.one2one.helper;

import com.one2one.entities.AcademicClass;
import com.one2one.entities.Course;
import com.one2one.entities.SubjectGroup;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.repositories.AcademicClassRepository;
import com.one2one.repositories.SubjectGroupRepository;
import com.one2one.requests.CourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseHelper {

    private final AcademicClassRepository academicClassRepository;
    private final SubjectGroupRepository subjectGroupRepository;

    public void setBaseData(Course course, RecordStatus status, Boolean forUpdate) {
        course.setRecordStatus(status);
//        if (forUpdate) {
//            subject.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
//        } else {
//            subject.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
//        }
    }

    public void getData(CourseRequest request, Course course) {
        AcademicClass academicClass = academicClassRepository.findById(request.getAcademicClassId()).orElseThrow(ResourceNotFoundException::new);
        SubjectGroup subjectGroup = subjectGroupRepository.findById(request.getAcademicClassId()).orElseThrow(ResourceNotFoundException::new);
        course.setAcademicClass(academicClass);
        course.setSubjectGroup(subjectGroup);
    }
}
