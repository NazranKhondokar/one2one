package com.one2one.controllers;

import com.one2one.entities.Course;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.CourseClassHelper;
import com.one2one.repositories.CourseRepository;
import com.one2one.requests.CourseClassListRequest;
import com.one2one.services.impl.CourseClassServiceImpl;
import com.one2one.validators.SubjectValidator;
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

import static com.one2one.constant.MessageConstants.COURSE_CLASS_SAVE;
import static com.one2one.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/course-class")
@Api(tags = "CourseClass's Data")
public class CourseClassController {

    private final CourseClassServiceImpl service;
    private final CourseClassHelper helper;
    private final SubjectValidator validator;
    private final CourseRepository courseRepository;

    @PostMapping("/save")
    @ApiOperation(value = "save CourseClass", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody CourseClassListRequest request, BindingResult bindingResult) {

        Optional<Course> course = courseRepository.findById(request.getCourseId());
        helper.setCourseClass(request, course.get(), RecordStatus.ACTIVE);
        service.save(course.get());
        return ok(success(null, COURSE_CLASS_SAVE).getJson());
    }
}

