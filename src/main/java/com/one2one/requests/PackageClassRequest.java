package com.one2one.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PackageClassRequest {

    @NotNull
    private Long academicClassId;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long studentUserId;

    @NotNull
    private Long teacherUserId;
    private Boolean packageClassIsPaid;
    private Boolean packageClassIsEnrolled;
}

