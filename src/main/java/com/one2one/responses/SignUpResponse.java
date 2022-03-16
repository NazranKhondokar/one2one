package com.one2one.responses;

import com.one2one.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.one2one.constant.ResponseStatus.SUCCESS;

@Data
@NoArgsConstructor
public class SignUpResponse {

    private String userName;
    private String email;
    private String mobile;
    private String status;

    public static SignUpResponse from(User user) {
        SignUpResponse response = new SignUpResponse();
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setMobile(user.getMobile());
        response.setStatus(SUCCESS);
        return response;
    }
}
