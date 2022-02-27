package com.one2one.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpResponse {

    private String userName;
    private String email;
    private String mobile;
    private String status;
}
