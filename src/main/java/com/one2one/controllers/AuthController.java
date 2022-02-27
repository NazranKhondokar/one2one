package com.one2one.controllers;

import com.one2one.entities.Role;
import com.one2one.entities.User;
import com.one2one.enums.RoleName;
import com.one2one.exceptions.AppException;
import com.one2one.repositories.RoleRepository;
import com.one2one.repositories.UserRepository;
import com.one2one.requests.LoginRequest;
import com.one2one.requests.SignUpRequest;
import com.one2one.responses.ApiResponse;
import com.one2one.responses.BasicResponse;
import com.one2one.responses.LoginResponse;
import com.one2one.responses.SignUpResponse;
import com.one2one.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@Api(tags = "Authentication")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getMobileOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        // set headers here
        response.addHeader("Access-Control-Expose-Headers", jwt);
        response.addHeader("tokenType", "Bearer");

        Optional<User> user = userRepository.findByMobileOrEmail(loginRequest.getMobileOrEmail(), loginRequest.getMobileOrEmail());

        if (response.getStatus() == 200) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setEmail(user.get().getEmail());
            loginResponse.setStatus("Success");
            loginResponse.setUser_name(user.get().getUser_name());
            loginResponse.setRoles(user.get().getRoles());

//        User user = new User();
//        user.setUsername(loginRequest.getUsernameOrEmail());

            return ResponseEntity.ok(loginResponse);

        } else if (response.getStatus() == 401) {
            System.out.println("##I am called.");
            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setResult(false);
            basicResponse.setMessage("Email, Mobile or Password is not correct. Please check again.");

            return ResponseEntity.created(URI.create("basicResponse")).body(basicResponse);
        } else {
            System.out.println("##I am called 2");
            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setResult(false);
            basicResponse.setMessage("Something went wrong.");

            return ResponseEntity.created(URI.create("basicResponse")).body(basicResponse);
        }


    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "User Email is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByMobile(signUpRequest.getMobile())) {
            return new ResponseEntity(new ApiResponse(false, "Mobile number already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUserName(),
                signUpRequest.getEmail(), signUpRequest.getMobile(), signUpRequest.getPassword(), signUpRequest.isActive(), signUpRequest.getRoles());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_AGENT)
                .orElseThrow(() -> new AppException("User Role not set."));

        userRole.setStatusName("ACTIVE");

        user.setRoles(Collections.singleton(userRole));


        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{userEmail}")
                .buildAndExpand(result.getEmail()).toUri();

        SignUpResponse signUpResponse = new SignUpResponse();

        signUpResponse.setEmail(user.getEmail());
        signUpResponse.setUser_name(user.getUser_name());
        signUpResponse.setMobile(user.getMobile());
        signUpResponse.setStatus("success");

        return ResponseEntity.ok(signUpResponse);
    }
}