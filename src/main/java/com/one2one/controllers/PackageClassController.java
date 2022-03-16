package com.one2one.controllers;

import com.one2one.entities.Package;
import com.one2one.enums.RecordStatus;
import com.one2one.helper.PackageClassHelper;
import com.one2one.repositories.PackageRepository;
import com.one2one.requests.PackageClassListRequest;
import com.one2one.services.impl.PackageClassServiceImpl;
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

import static com.one2one.constant.MessageConstants.PACKAGE_CLASS_SAVE;
import static com.one2one.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/package-class")
@Api(tags = "PackageClass's Data")
public class PackageClassController {

    private final PackageClassServiceImpl service;
    private final PackageClassHelper helper;
    private final SubjectValidator validator;
    private final PackageRepository packageRepository;

    @PostMapping("/save")
    @ApiOperation(value = "save PackageClass", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PackageClassListRequest request, BindingResult bindingResult) {

        Optional<Package> packageEntity = packageRepository.findById(request.getPackageId());
        helper.setPackageClass(request, packageEntity.get(), RecordStatus.ACTIVE);
        service.save(packageEntity.get());
        return ok(success(null, PACKAGE_CLASS_SAVE).getJson());
    }
}

