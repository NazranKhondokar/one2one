package com.one2one.controllers;

import com.one2one.entities.Course;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.requests.CourseRequest;
import com.one2one.responses.CourseResponse;
import com.one2one.services.impl.CourseServiceImpl;
import com.one2one.utils.CommonDataHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.one2one.constant.MessageConstants.*;
import static com.one2one.exceptions.ApiError.fieldError;
import static com.one2one.utils.ResponseBuilder.error;
import static com.one2one.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/course")
@Api(tags = "Course's Data")
public class CourseController {

    private final CourseServiceImpl service;
    private final CommonDataHelper helper;

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get course by id", response = CourseResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<CourseResponse> response = Optional.ofNullable(service.findById(id).map(CourseResponse::from)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }

    @PostMapping("/save")
    @ApiOperation(value = "save course", response = CourseResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody CourseRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return badRequest().body(error(fieldError(bindingResult)).getJson());

        Course course = service.save(request);
        return ok(success(CourseResponse.from(course), COURSE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update course", response = CourseResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody CourseRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return badRequest().body(error(fieldError(bindingResult)).getJson());

        Course course = service.update(request);
        return ok(success(CourseResponse.from(course), COURSE_UPDATE).getJson());
    }

    @PutMapping("/change-record-status/{id}/{status}")
    @ApiOperation(value = "course record status update", response = CourseResponse.class)
    public ResponseEntity<JSONObject> changeRecordStatus(@PathVariable Long id, @PathVariable RecordStatus status) {

        Course course = service.update(id, status);
        return ok(success(CourseResponse.from(course), RECORD_STATUS_UPDATE).getJson());
    }
}

