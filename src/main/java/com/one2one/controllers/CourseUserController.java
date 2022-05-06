package com.one2one.controllers;

import com.one2one.entities.Course;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.CourseUserHelper;
import com.one2one.repositories.CourseRepository;
import com.one2one.requests.CourseUserListRequest;
import com.one2one.services.CourseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static com.one2one.constant.MessageConstants.COURSE_USER_SAVE;
import static com.one2one.utils.ResponseBuilder.error;
import static com.one2one.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/course-user")
@Api(tags = "CourseUser's Data")
public class CourseUserController {

    private final CourseUserService service;
    private final CourseUserHelper helper;
    private final CourseRepository courseRepository;

    @PostMapping("/save")
    @ApiOperation(value = "save CourseUser", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody CourseUserListRequest request, BindingResult bindingResult) {

        Optional<Course> course = courseRepository.findById(request.getCourseId());
        if (course.isPresent()) {
            helper.setCourseUserDtoToEntity(request, course.get(), RecordStatus.ACTIVE);
            service.save(course.get());
            return ok(success(null, COURSE_USER_SAVE).getJson());
        } else return badRequest().body(error(null, "Course not found!!").getJson());
    }
}

