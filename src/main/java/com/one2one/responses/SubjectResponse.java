package com.one2one.responses;

import com.one2one.entities.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectResponse {

    private Long id;
    private String subjectName;
    private String subjectNameBn;
    private String subjectCode;
    private Long subjectTypeId;

    public static SubjectResponse from(Subject subject) {
        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setSubjectName(subject.getSubjectName());
        response.setSubjectNameBn(subject.getSubjectNameBn());
        response.setSubjectCode(subject.getSubjectCode());
        return response;
    }
}

