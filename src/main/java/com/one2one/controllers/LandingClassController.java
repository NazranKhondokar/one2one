package com.one2one.controllers;


import com.one2one.entities.LandingClass;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.requests.LandingClassRequest;
import com.one2one.responses.LandingClassResponse;
import com.one2one.responses.SubjectResponse;
import com.one2one.services.impl.LandingClassImpl;
import com.one2one.utils.CommonDataHelper;
import com.one2one.utils.PaginatedResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.one2one.constant.MessageConstants.*;
import static com.one2one.exceptions.ApiError.fieldError;
import static com.one2one.utils.ResponseBuilder.*;
import static com.one2one.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/landingClass")
@Api(tags = "LandingClass's Data")
public class LandingClassController {
    private final CommonDataHelper helper;
    private final LandingClassImpl service;


    @GetMapping("/list")
    @ApiOperation(value = "get landingClass", response = SubjectResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "landingClassId", defaultValue = "1") Long landingClassId
    ) {
        helper.setPageSize(page, size);

        PaginatedResponse response = new PaginatedResponse();
        Map<String, Object> landngClassMap = service.searchLandingClass(landingClassId, page, size, sortBy);
        List<LandingClass> responses = (List<LandingClass>) landngClassMap.get("lists");
        List<LandingClassResponse> customResponses = responses.stream().map(LandingClassResponse::from).collect(Collectors.toList());
        helper.getCommonData(page, size, landngClassMap, response, customResponses);
        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get landingClass by id", response = LandingClassResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {
        Optional<LandingClassResponse> response = Optional.ofNullable(service.findById(id).map(LandingClassResponse::from)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }

    @PostMapping("/save")
    @ApiOperation(value = "save landingClass", response = LandingClassResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody LandingClassRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }
        LandingClass landingClass = service.save(request);
        return ok(success(LandingClassResponse.from(landingClass), LANDINGCLASS_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update landingClass", response = LandingClassResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody LandingClassRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        LandingClass landingClass = service.update(request);
        return ok(success(LandingClassResponse.from(landingClass), LANDINGCLASS_UPDATE).getJson());
    }
}
