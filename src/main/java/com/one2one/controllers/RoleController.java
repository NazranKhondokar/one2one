package com.one2one.controllers;

import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.responses.RoleResponse;
import com.one2one.services.impl.RoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.one2one.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/role")
@Api(tags = "Role's Data")
public class RoleController {

    private final RoleServiceImpl service;

    @GetMapping("/all")
    @ApiOperation(value = "Get all roles", response = RoleResponse.class)
    public ResponseEntity<JSONObject> findAll() {

        List<RoleResponse> responses = service.findAll().stream().map(RoleResponse::from).collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get role by id", response = RoleResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<RoleResponse> response = Optional.ofNullable(service.findById(id).map(RoleResponse::from)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }
}

