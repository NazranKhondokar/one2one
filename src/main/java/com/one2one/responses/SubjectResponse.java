package com.one2one.responses;

import com.one2one.entities.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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
        BeanUtils.copyProperties(subject, response);
        return response;
    }
}

