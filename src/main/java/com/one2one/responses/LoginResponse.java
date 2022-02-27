package com.one2one.responses;

import com.one2one.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class LoginResponse {

    private String status;
    private String userName;
    private String email;
    private Set<Role> roles;
}
