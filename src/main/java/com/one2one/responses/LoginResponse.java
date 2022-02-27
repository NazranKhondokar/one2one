package com.one2one.responses;


import com.one2one.entities.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class LoginResponse {

    private String status;
    private String userName;
    private String email;
    private Set<Role> roles;
}
