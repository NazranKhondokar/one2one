package com.one2one.responses;

import com.one2one.entities.Role;
import com.one2one.enums.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleResponse {

    private Long id;
    private RoleName roleName;

    public static RoleResponse from(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setRoleName(role.getName());
        return response;
    }
}

