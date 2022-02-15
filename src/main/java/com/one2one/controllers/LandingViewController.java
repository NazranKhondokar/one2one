package com.one2one.controllers;

import com.one2one.entities.LandingView;
import com.one2one.entities.Subject;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.requests.LandingViewRequest;
import com.one2one.responses.LandingViewResponse;
import com.one2one.responses.SubjectResponse;
import com.one2one.services.impl.LandingViewImpl;
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
@RequestMapping(path = "api/v1/landingView")
@Api(tags = "LandingView's Data")
public class LandingViewController {
    private final CommonDataHelper helper;
    private final LandingViewImpl service;


    @GetMapping("/list")
    @ApiOperation(value = "get landingView", response = SubjectResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "view_id", defaultValue = "1") Long landingviewId
    ) {
        helper.setPageSize(page, size);

        PaginatedResponse response = new PaginatedResponse();
        Map<String, Object>landingViewMap = service.searchLandingView(landingviewId, page, size, sortBy);
        List<LandingView> responses = (List<LandingView>) landingViewMap.get("lists");
        List<LandingViewResponse> customResponses = responses.stream().map(LandingViewResponse::from).collect(Collectors.toList());
        helper.getCommonData(page, size, landingViewMap, response, customResponses);
        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get landingView by id", response = LandingViewResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {
        Optional<LandingViewResponse> response = Optional.ofNullable(service.findById(id).map(LandingViewResponse::from)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }

    @PostMapping("/save")
    @ApiOperation(value = "save landingView", response = LandingViewResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody LandingViewRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }
        LandingView landingView = service.save(request);
        return ok(success(LandingViewResponse.from(landingView), LANDINGVIEW_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update landingView", response = LandingViewResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody LandingViewRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        LandingView landingView = service.update(request);
        return ok(success(LandingViewResponse.from(landingView), LANDINGVIEWE_UPDATE).getJson());
    }
}
