package com.one2one.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CourseUserListRequest {

    private Long courseId;
    private List<CourseUserRequest> courseUsers;
}

