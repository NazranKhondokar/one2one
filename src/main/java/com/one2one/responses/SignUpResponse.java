package com.one2one.responses;

import com.one2one.entities.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class SignUpResponse {

    private String userName;
    private String email;
    private String mobile;
    private String status;

    public static SubjectResponse from(Subject subject) {
        SubjectResponse response = new SubjectResponse();
        BeanUtils.copyProperties(subject, response);
        return response;
    }
}
