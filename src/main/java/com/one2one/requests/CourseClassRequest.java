package com.one2one.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CourseClassRequest {

    @NotNull
    private Long academicClassId;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long studentUserId;

    @NotNull
    private Long teacherUserId;
    private Boolean courseClassIsPaid;
    private Boolean courseClassIsEnrolled;
}

