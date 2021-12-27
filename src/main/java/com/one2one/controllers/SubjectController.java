package com.one2one.controllers;

import com.one2one.entities.Subject;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.requests.SubjectRequest;
import com.one2one.responses.SubjectResponse;
import com.one2one.services.impl.SubjectServiceImpl;
import com.one2one.utils.CommonDataHelper;
import com.one2one.utils.PaginatedResponse;
import com.one2one.validators.SubjectValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.one2one.constant.MessageConstants.*;
import static com.one2one.exceptions.ApiError.fieldError;
import static com.one2one.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/subject")
@Api(tags = "Subject's Data")
public class SubjectController {

    private final SubjectServiceImpl service;
    private final SubjectValidator validator;
    private final CommonDataHelper helper;

    @GetMapping("/list")
    @ApiOperation(value = "get subject", response = SubjectResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "subjectName", defaultValue = "") String subjectName,
            @RequestParam(value = "subjectTypeId", defaultValue = "") Long subjectTypeId
    ) {
        helper.setPageSize(page, size);

        PaginatedResponse response = new PaginatedResponse();
        Map<String, Object> subjectMap = service.searchSubject(subjectName, subjectTypeId, page, size, sortBy);

        List<Subject> responses = (List<Subject>) subjectMap.get("lists");

        List<SubjectResponse> customResponses = responses.stream()
                .map(SubjectResponse::from)
                .collect(Collectors.toList());

        helper.getCommonData(page, size, subjectMap, response, customResponses);

        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get subject by id", response = SubjectResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<SubjectResponse> response = Optional.ofNullable(service.findById(id).map(SubjectResponse::from)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }

    @PostMapping("/save")
    @ApiOperation(value = "save subject", response = SubjectResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody SubjectRequest request, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Subject subject = request.to(request);
        service.save(subject);
        return ok(success(SubjectResponse.from(subject), SUBJECT_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update subject", response = SubjectResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody SubjectRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Subject subject = service.findById(request.getId()).orElseThrow(
                () -> new ResourceNotFoundException("subject type not found" + request.getId())
        );
        request.update(request, subject);
        service.update(subject);
        return ok(success(SubjectResponse.from(subject), SUBJECT_UPDATE).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("delete subject by id")
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        Subject subject = service.findById(id).orElseThrow(ResourceNotFoundException::new);
        service.delete(subject);
        return ok(success(null, SUBJECT_DELETE).getJson());
    }

//    @PostMapping("/change-record-status/{id}/{status}")
//    @ApiOperation(value = "subject record status update", response = SubjectResponse.class)
//    public ResponseEntity<JSONObject> changeRecordStatus(@PathVariable Long id, @PathVariable RecordStatus status) {
//
//        Subject subject = service.findById(id);
//        service.update(subject, status);
//
//        return ok(success(SubjectResponse.from(shift), RECORD_STATUS_UPDATE).getJson());
//    }
}

