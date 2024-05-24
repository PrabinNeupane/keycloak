package com.keyclock.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/user/demo")
    @PreAuthorize("hasRole('user')")
    public String hello(){
    return "Hello this is basic keycloak practice";
}

    @GetMapping("/admin/demo")
    @PreAuthorize("hasRole('ROLE_realm_admin')")
    public String hello2() {
        return "Hello from Spring boot & Keycloak Admin";
    }

    @PostMapping("/user-deleted")
    public ResponseEntity<String> handleUserDeletion(){
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }
}
