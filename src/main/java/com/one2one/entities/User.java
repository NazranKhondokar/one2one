package com.one2one.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "USER_EMAIL"
        }),
        @UniqueConstraint(columnNames = {
                "USER_MOBILE"
        })
})
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @NaturalId
    @Column(name = "USER_EMAIL", length = 40)
    @Email
    private String email;

    @Column(name = "USER_MOBILE", length = 40)
    private String mobile;

    @Column(name = "USER_PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }


    public User(@NotBlank @Size(max = 40) String user_name, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 40) String mobile, @NotBlank @Size(max = 100) String password, @NotBlank boolean isActive, Set<Role> roles) {
        this.userName = user_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return userName;
    }

    public void setUser_name(String user_name) {
        this.userName = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}