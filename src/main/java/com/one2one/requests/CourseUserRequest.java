package com.one2one.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CourseUserRequest {

    @NotNull
    private Long subjectId;

    @NotNull
    private Long studentUserId;

    private Integer completion;
    private Boolean hasCourseEnrolled;
}

