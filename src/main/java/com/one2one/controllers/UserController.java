package com.one2one.controllers;

import com.one2one.entities.User;
import com.one2one.helper.UserHelper;
import com.one2one.requests.AddressListRequest;
import com.one2one.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.one2one.exceptions.ApiError.fieldError;
import static com.one2one.utils.CollectionUtils.distinct;
import static com.one2one.utils.ResponseBuilder.error;
import static com.one2one.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Api(tags = "User's data")
public class UserController {

    private final UserServiceImpl service;
    private final UserHelper helper;

    @PostMapping("/save/addresses/{userId}")
    @ApiOperation(value = "save addresses by user id", response = String.class)
    public ResponseEntity<JSONObject> saveAddresses(
            @Valid @PathVariable("userId") @NotNull @Min(1) Long userId,
            @Valid @RequestBody AddressListRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        User user = service.findUserById(userId);
        user.addAddresses(distinct(helper.getAddresses(request.getAddresses())));
        service.save(user);
        return ok(success(null, "success").getJson());
    }
}