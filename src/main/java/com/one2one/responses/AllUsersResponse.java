package com.one2one.responses;

public class AllUsersResponse {

    private Long id;
    private String user_name;
    private String email;
    private String mobile;

    public AllUsersResponse(Long id, String user_name, String email, String mobile) {
        this.id = id;
        this.user_name = user_name;
        this.email = email;
        this.mobile = mobile;
    }

    public AllUsersResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
