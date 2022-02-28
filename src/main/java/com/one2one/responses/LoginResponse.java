package com.one2one.responses;

import com.one2one.entities.Role;
import com.one2one.entities.User;
import lombok.Data;

import java.util.Set;

import static com.one2one.constant.ResponseStatus.SUCCESS;

@Data
public class LoginResponse {

    private String status;
    private String userName;
    private String email;
    private Set<Role> roles;

    public static LoginResponse from(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(user.getEmail());
        loginResponse.setStatus(SUCCESS);
        loginResponse.setUserName(user.getUserName());
        loginResponse.setRoles(user.getRoles());
        return loginResponse;
    }
}
