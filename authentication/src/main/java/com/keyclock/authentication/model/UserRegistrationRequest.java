package com.keyclock.authentication.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRegistrationRequest {

    private String username;
    private String email;
    private String password;
    List<String> role;
}
