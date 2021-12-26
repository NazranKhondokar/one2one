package com.one2one.requests;

import com.one2one.entities.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SubjectRequest {

    private Long id;

    @NotBlank
    private String subjectName;

    @NotBlank
    private String subjectNameBn;
    private String subjectCode;
    private Long subjectTypeId;

    public Subject to(SubjectRequest request) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(request, subject);
        return subject;
    }

    public void update(SubjectRequest request, Subject subject) {
        BeanUtils.copyProperties(request, subject);
    }
}

