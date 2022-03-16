package com.one2one.controllers;

import com.one2one.entities.Address;

import com.one2one.entities.Subject;
import com.one2one.enums.RecordStatus;
import com.one2one.exceptions.ResourceNotFoundException;
import com.one2one.requests.AddressRequest;
import com.one2one.responses.AddressResponse;

import com.one2one.responses.SubjectResponse;
import com.one2one.services.impl.AddressServiceImpl;
import com.one2one.utils.CommonDataHelper;
import com.one2one.utils.PaginatedResponse;
import com.one2one.validators.AddressValidator;

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
@RequestMapping(path = "api/address")
@Api("Address's Data")
public class AddressController {
    private final AddressServiceImpl service;
    private final AddressValidator validator;
    private final CommonDataHelper helper;

    @GetMapping("/list")
    @ApiOperation(value = "get AddressList", response = AddressResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {
        helper.setPageSize(page, size);

        PaginatedResponse response = new PaginatedResponse();

        Map<String, Object> addressMap = service.searchAddress(page, size, sortBy);

        List<Address> responses = (List<Address>) addressMap.get("lists");

        List<AddressResponse> customResponses = responses.stream()
                .map(AddressResponse::from)
                .collect(Collectors.toList());

        helper.getCommonData(page, size, addressMap, response, customResponses);


        return ok(paginatedSuccess(response).getJson());
    }


    @GetMapping("/find/{id}")
    @ApiOperation(value = "get Address by id", response = AddressResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<AddressResponse> response = Optional.ofNullable(service.findById(id).map(AddressResponse::from)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }

    @PostMapping("/save")
    @ApiOperation(value = "save Address", response = AddressResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody AddressRequest request, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Address address = service.save(request);
        return ok(success(AddressResponse.from(address), ADDRESS_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update Address", response = AddressResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody AddressRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Address address = service.update(request);
        return ok(success(AddressResponse.from(address), ADDRESS_UPDATE).getJson());
    }

    @PutMapping("/change-record-status/{id}/{status}")
    @ApiOperation(value = "Address record status update", response = AddressResponse.class)
    public ResponseEntity<JSONObject> changeRecordStatus(@PathVariable Long id, @PathVariable RecordStatus status) {

        Address address = service.update(id, status);
        return ok(success(AddressResponse.from(address), RECORD_STATUS_UPDATE).getJson());
    }

}
